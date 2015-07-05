package my.interview.samples;


@SuppressWarnings("unused")
public class MyHashMap<K,V> {

	int size;

	transient Entry<K,V>[] table;

	int length;
	int modCount;


	public MyHashMap(int capacity)
	{
		this.length = capacity;
		table = new Entry[length];
	}

	public static class Entry<K,V>
	{
		Object k,v;
		Entry<K,V> next;
		int hash;

		public Entry(Object k, Object v, Entry<K,V> e,int hash)
		{
			this.k = k;
			this.v = v;
			this.next = e;
			this.hash = hash;
		}
	}

	public int hash(Object k)
	{
		int h = 0;

		if(k==null) return 0;

		h^= k.hashCode();

		return h^h>>>4;
	}

	public int getIndex(int hashCode, int length)
	{
		return hashCode & (length-1);
	}

	public void putForNullKey(Object key, Object value)
	{	
		add(0,key,value,0);
	}

	public void put(Object key, Object value)
	{
		int hashCode = hash(key);
		int index = getIndex(hashCode,length);
		modCount++;
		if(key==null)
		{
			putForNullKey(key,value);
		}else
		{
			for(Entry<K,V> e=table[index];e!=null;e=e.next)
			{
				if(e.hash==hashCode && key.equals(e.k))
				{
					e.v = value;
					return;
				}
			}

			add(index,key,value,hashCode);
		}
	}

	public void add(int index,Object k, Object v, int hash)
	{
		Entry<K,V> e = table[index];

		size++;

		Entry<K,V> newEntry = new Entry<K, V>(k,v,e,hash);

		table[index] = newEntry;
	}

	public Object get(Object key)
	{
		if(key == null) return getForNullKey(key);

		int hashCode = hash(key);
		int index = getIndex(hashCode, length);
		
		for(Entry<K,V> e= table[index];e!=null;e=e.next)
		{
			if(e.hash==hashCode && e.k.equals(key))
				return e.v;
		}
		return null;

	}

	public Object getForNullKey(Object key)
	{	
		for(Entry<K,V> e = table[0];e!=null;e=e.next)
		{
			if(e.k==null) return e.v;
		}
		return null;
	}
	
	public Entry<K,V> removeKey(Object key)
	{
		Entry<K,V> prev,e;
		int hash = (key==null)?0:hash(key);
		int index = getIndex(hash,length);
		e = table[index];
		prev = e;

		while(e != null)
		{
			Entry<K,V> next = e.next;
			if(e.hash == hash && e.k == key)
			{               
				modCount++;
				size--;
				//First entry in the linkedlist
				if(prev == e)
				{
					table[index] = next;
				}else
				{
					prev.next = next;
				}
				return e;
			}
			prev = e;
			e = e.next;

		}
		return e;
	}

	public class HashMapIterator<E>
	{
		Entry<K,V> current, next;
		int expectedModCount;
		int index;
		HashMapIterator()
		{
			expectedModCount = modCount;
			while(index <= length && (next = table[index])== null)
			{
				index++;
			}
		}

		boolean hasNext()
		{
			return next != null;
		}

		public Entry<K,V> next() throws Exception
		{

			if(expectedModCount != modCount) throw new Exception();

			if(next == null) throw new Exception();

			Entry<K,V> e = next;

			if((next = e.next)==null)
			{
				while(index <=length && (next = table[index])==null)
				{
					index++;
				}
			}
			current = e;
			return e;
		}

		void remove() throws Exception
		{
			if(expectedModCount != modCount) throw new Exception();
			Object key = current.k;
			current = null;
			removeKey(key);
			expectedModCount = modCount;
		}

	}
}
