package org.frameworkset.nosql;

import org.frameworkset.nosql.redis.RedisFactory;
import org.frameworkset.nosql.redis.RedisHelper;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.util.Slowlog;

import java.util.List;

public class RedisTest {

	public RedisTest() {
		// TODO Auto-generated constructor stub
	}
	@Test
	public void get()
	{
		RedisHelper redisHelper = null;
		try
		{
			redisHelper = RedisFactory.getRedisHelper();
			redisHelper.set("test", "value1");
			String value = redisHelper.get("test");
			System.out.println("test="+value);
			redisHelper.setex("foo", 1,"fasdfasf");//指定缓存有效期1秒
			
			System.out.println("foo ttl="+redisHelper.ttl("foo"));//获取有效期
			value = redisHelper.get("foo");//获取数据
			System.out.println("foo="+value);
			//删除数据
			redisHelper.del("foo");
			value = redisHelper.getSet("fowwero","test");
			
			System.out.println("fowwero="+value);
			value = redisHelper.getSet("fowwero","eeee");//获取后修改数据
			System.out.println("fowwero="+value);
			
			value = redisHelper.get("fowwero");
			
			System.out.println("fowwero="+value);
		}
		finally
		{
			if(redisHelper != null)
				redisHelper.release();
		}
	}
	@Test
	public void testconfigGet(){
		RedisHelper redisHelper = null;
		try
		{
			redisHelper = RedisFactory.getRedisHelper();
			Jedis jedis = redisHelper.getJedis();
			System.out.println(jedis.info());

			List<String> redis_maxclients = jedis.configGet("maxclients");
			List<String>  redis_maxmemory = jedis.configGet("maxmemory");
			System.out.println(redis_maxmemory);
			List<Slowlog> slowlogs = jedis.slowlogGet();
			System.out.println(slowlogs.size());
		}
		finally
		{
			if(redisHelper != null)
				redisHelper.release();
		}
	}
	 

}
