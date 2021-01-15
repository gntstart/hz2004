package com.hzjc.hz2004.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import com.hzjc.hz2004.base.SystemConfig;
import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;

public class MemcachedUtil {
	static private MemcachedClient mcc = null;
	static private String flag = "0";
	static private String serverlist = SystemConfig.getSystemConfig("Memcached.serverlist", "127.0.0.1:11211");

    /**
     * 本地锁
     */
    static private Map<String,Timer> LOCK_TIMER_MAP = new HashMap<>();
    
	static public boolean lock(String name){
		String keyname = "_SpyMemcachedManager_lock_" + name;
		
		//add如果存在key，会返回false，设置60秒超时，防止死锁
		if(getBooleanValue(getMcc().add(keyname, 60, "1"))){
			//获取到原子操作还不行，还需要保持在调用unlock之前不超时，防止过期
			Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    //30秒触发一次，重新设置锁定对象生存期为60秒
                	getMcc().set(keyname, 60,  "1");
                }
            }, 1000, 1000 * 30);
            LOCK_TIMER_MAP.put(name, timer);
            
            return true;
		}else{
			return false;
		}
	}
	
	static public boolean unlock(String name){
		/**
		 * 确保lock没成功，不要调用unlock
		 */
		String keyname = "_SpyMemcachedManager_lock_" + name;
        //先判断定时器是否存在，避免被其他人解锁
        if (LOCK_TIMER_MAP.containsKey(name)) {
            //移除并关闭计时器
            LOCK_TIMER_MAP.remove(name).cancel();
        }
        getMcc().delete(keyname);
        
        return true;
	}
	
	static public MemcachedClient getMcc() {
		if (mcc != null)
			return mcc;

		synchronized (flag) {
			if (mcc != null)
				return mcc;

			try {
				mcc = new MemcachedClient(AddrUtil.getAddresses(serverlist));
			} catch (Exception e) {
				System.out.println("memcached无法初始化！");
				e.printStackTrace();
				System.exit(0);
			}
		}

		return mcc;
	}

	/**
	 * 单位分钟，生存期为秒
	 * @param key
	 * @param value
	 * @param expire
	 * @return
	 */
	public static boolean set(String key, Object value, int expire) {
		OperationFuture<Boolean> f = getMcc().set(key, expire*60, value);
		return getBooleanValue(f);
	}
	
	public static boolean set(String key, Object value) {
		OperationFuture<Boolean> f = getMcc().set(key, 0, value);
		return getBooleanValue(f);
	}

	public static boolean add(String key, Object value, int expire) {
		OperationFuture<Boolean> f = getMcc().add(key, expire*60, value);
		return getBooleanValue(f);
	}
	
	public static boolean addS(String key, Object value, int expire) {
		OperationFuture<Boolean> f = getMcc().add(key, expire, value);
		return getBooleanValue(f);
	}
	
	public static boolean add(String key, Object value) {
		OperationFuture<Boolean> f = getMcc().add(key, 0, value);
		return getBooleanValue(f);
	}
	
	static  public Object get(String key) {       
	        return getMcc().get(key);       
	}    
	
	static public boolean delete(String key) {       
		OperationFuture<Boolean> f = getMcc().delete(key);       
        return getBooleanValue(f);       
    }
	
	private static boolean getBooleanValue(OperationFuture<Boolean> f) {
		try {
			Boolean bool = f.get(MemcachedUtil.DEFAULT_TIMEOUT, MemcachedUtil.DEFAULT_TIMEUNIT);
			return bool.booleanValue();
		} catch (Exception e) {
			f.cancel();
			return false;
		}
	}

	public static int DEFAULT_TIMEOUT = 5;
	public static TimeUnit DEFAULT_TIMEUNIT = TimeUnit.SECONDS;
	
	public static void main(String argc[]) throws Exception{
		boolean b = MemcachedUtil.addS("11111", "1", 3);
		System.out.println(b);
		b = MemcachedUtil.addS("11111", "1", 3);
		System.out.println(b);
		System.in.read();
		b = MemcachedUtil.addS("11111", "1", 3);
		System.out.println(b);
		
		//SpyMemcachedManager.unlock("key1");
		
		if(MemcachedUtil.lock("key1")){
			System.out.println("锁成功！");
		}else{
			System.out.println("锁失败！");
		}
		
		if(MemcachedUtil.lock("key1")){
			System.out.println("锁成功！");
		}else{
			System.out.println("锁失败！");
		}
		
		if(MemcachedUtil.lock("key1")){
			System.out.println("锁成功！");
		}else{
			System.out.println("锁失败！");
		}
		MemcachedUtil.unlock("key1");
		if(MemcachedUtil.lock("key1")){
			System.out.println("锁成功！");
		}else{
			System.out.println("锁失败！");
		}
		
		System.exit(0);
	}
}
