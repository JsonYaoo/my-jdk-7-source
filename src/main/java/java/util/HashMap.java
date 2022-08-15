/*
 * Copyright (c) 1997, 2010, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package java.util;
import java.io.*;

/**
 * 20210628
 * A. Map接口的基于哈希表的实现。 此实现提供所有可选的映射操作，并允许null值和null键。 （HashMap 类大致等同于 Hashtable，只是它是非同步的并且允许null值。）
 *    该类不保证映射的顺序；特别是，它不保证顺序会随着时间的推移保持不变。
 * B. 此实现为基本操作（get 和 put）提供恒定时间性能，假设散列函数在存储桶中正确分散元素。 迭代集合视图需要的时间与 HashMap 实例的“容量”（桶的数量）
 *    加上它的大小（键值映射的数量）成正比。 因此，如果迭代性能很重要，则不要将初始容量设置得太高（或负载因子太低），这一点非常重要。
 * C. HashMap的实例有两个影响其性能的参数：初始容量和负载因子。 容量是哈希表中的桶数，初始容量就是哈希表创建时的容量。
 *    负载因子是衡量哈希表在其容量自动增加之前允许达到多满的指标。 当哈希表中的条目数超过负载因子和当前容量的乘积时，重新哈希表（即重建内部数据结构），
 *    使哈希表具有大约两倍的桶数。
 * D. 作为一般规则，默认负载因子 (0.75) 在时间和空间成本之间提供了很好的权衡。 较高的值会减少空间开销，但会增加查找成本（反映在 HashMap 类的大多数操作中，
 *    包括get 和put）。在设置其初始容量时，应考虑映射中的预期条目数及其负载因子，以尽量减少重新哈希操作的次数。 如果初始容量大于最大条目数除以负载因子，
 *    则不会发生重新哈希操作。
 * E. 如果要在一个HashMap实例中存储许多映射，则创建具有足够大容量的映射将允许更有效地存储映射，而不是让它根据需要执行自动重新散列以增加表。
 * F. 请注意，此实现不是同步的。 如果多个线程并发访问一个哈希映射，并且至少有一个线程在结构上修改了映射，则必须在外部进行同步。
 *   （结构修改是添加或删除一个或多个映射的任何操作；仅更改与实例已包含的键关联的值不是结构修改。）这通常是通过同步一些自然封装映射的对象来完成的.
 * G. 如果不存在此类对象，则应使用 {@link Collections#synchronizedMap Collections.synchronizedMap} 方法“包装”Map。 这最好在创建时完成，
 *    以防止对Map的意外不同步访问：
 *    Map m = Collections.synchronizedMap(new HashMap(...));
 * H. 此类的所有“集合视图方法”返回的迭代器都是快速失败的：如果在迭代器创建后的任何时间对映射进行结构修改，除了通过迭代器自己的 remove 方法外，迭代器将抛出一个
 *    { @link ConcurrentModificationException}。 因此，面对并发修改，迭代器快速而干净地失败，而不是在未来不确定的时间冒着任意、非确定性行为的风险。
 * I. 请注意，无法保证迭代器的快速失败行为，因为一般来说，在存在非同步并发修改的情况下不可能做出任何硬保证。
 *    快速失败的迭代器会尽最大努力抛出ConcurrentModificationException。 因此，编写一个依赖此异常来确保其正确性的程序是错误的：迭代器的快速失败行为应该仅用于检测错误。
 * J. This class is a member of the Java Collections Framework.
 */

/**
 * A.
 * Hash table based implementation of the <tt>Map</tt> interface.  This
 * implementation provides all of the optional map operations, and permits
 * <tt>null</tt> values and the <tt>null</tt> key.  (The <tt>HashMap</tt>
 * class is roughly equivalent to <tt>Hashtable</tt>, except that it is
 * unsynchronized and permits nulls.)  This class makes no guarantees as to
 * the order of the map; in particular, it does not guarantee that the order
 * will remain constant over time.
 *
 * B.
 * <p>This implementation provides constant-time performance for the basic
 * operations (<tt>get</tt> and <tt>put</tt>), assuming the hash function
 * disperses the elements properly among the buckets.  Iteration over
 * collection views requires time proportional to the "capacity" of the
 * <tt>HashMap</tt> instance (the number of buckets) plus its size (the number
 * of key-value mappings).  Thus, it's very important not to set the initial
 * capacity too high (or the load factor too low) if iteration performance is
 * important.
 *
 * C.
 * <p>An instance of <tt>HashMap</tt> has two parameters that affect its
 * performance: <i>initial capacity</i> and <i>load factor</i>.  The
 * <i>capacity</i> is the number of buckets in the hash table, and the initial
 * capacity is simply the capacity at the time the hash table is created.  The
 * <i>load factor</i> is a measure of how full the hash table is allowed to
 * get before its capacity is automatically increased.  When the number of
 * entries in the hash table exceeds the product of the load factor and the
 * current capacity, the hash table is <i>rehashed</i> (that is, internal data
 * structures are rebuilt) so that the hash table has approximately twice the
 * number of buckets.
 *
 * D.
 * <p>As a general rule, the default load factor (.75) offers a good tradeoff
 * between time and space costs.  Higher values decrease the space overhead
 * but increase the lookup cost (reflected in most of the operations of the
 * <tt>HashMap</tt> class, including <tt>get</tt> and <tt>put</tt>).  The
 * expected number of entries in the map and its load factor should be taken
 * into account when setting its initial capacity, so as to minimize the
 * number of rehash operations.  If the initial capacity is greater
 * than the maximum number of entries divided by the load factor, no
 * rehash operations will ever occur.
 *
 * E.
 * <p>If many mappings are to be stored in a <tt>HashMap</tt> instance,
 * creating it with a sufficiently large capacity will allow the mappings to
 * be stored more efficiently than letting it perform automatic rehashing as
 * needed to grow the table.
 *
 * F.
 * <p><strong>Note that this implementation is not synchronized.</strong>
 * If multiple threads access a hash map concurrently, and at least one of
 * the threads modifies the map structurally, it <i>must</i> be
 * synchronized externally.  (A structural modification is any operation
 * that adds or deletes one or more mappings; merely changing the value
 * associated with a key that an instance already contains is not a
 * structural modification.)  This is typically accomplished by
 * synchronizing on some object that naturally encapsulates the map.
 *
 * G.
 * If no such object exists, the map should be "wrapped" using the
 * {@link Collections#synchronizedMap Collections.synchronizedMap}
 * method.  This is best done at creation time, to prevent accidental
 * unsynchronized access to the map:<pre>
 *   Map m = Collections.synchronizedMap(new HashMap(...));</pre>
 *
 * H.
 * <p>The iterators returned by all of this class's "collection view methods"
 * are <i>fail-fast</i>: if the map is structurally modified at any time after
 * the iterator is created, in any way except through the iterator's own
 * <tt>remove</tt> method, the iterator will throw a
 * {@link ConcurrentModificationException}.  Thus, in the face of concurrent
 * modification, the iterator fails quickly and cleanly, rather than risking
 * arbitrary, non-deterministic behavior at an undetermined time in the
 * future.
 *
 * I.
 * <p>Note that the fail-fast behavior of an iterator cannot be guaranteed
 * as it is, generally speaking, impossible to make any hard guarantees in the
 * presence of unsynchronized concurrent modification.  Fail-fast iterators
 * throw <tt>ConcurrentModificationException</tt> on a best-effort basis.
 * Therefore, it would be wrong to write a program that depended on this
 * exception for its correctness: <i>the fail-fast behavior of iterators
 * should be used only to detect bugs.</i>
 *
 * J.
 * <p>This class is a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 *
 * @author  Doug Lea
 * @author  Josh Bloch
 * @author  Arthur van Hoff
 * @author  Neal Gafter
 * @see     Object#hashCode()
 * @see     Collection
 * @see     Map
 * @see     TreeMap
 * @see     Hashtable
 * @since   1.2
 */

public class HashMap<K,V> extends AbstractMap<K,V> implements Map<K,V>, Cloneable, Serializable
{

    /**
     * The default initial capacity - MUST be a power of two.
     */
    // 默认初始容量 - 必须是 2 的幂。
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

    /**
     * The maximum capacity, used if a higher value is implicitly specified
     * by either of the constructors with arguments.
     * MUST be a power of two <= 1<<30.
     */
    // 最大容量，在两个带参数的构造函数隐式指定更高值时使用。 必须是 2 的幂 <= 1<<30。
    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * The load factor used when none specified in constructor.
     */
    // 在构造函数中未指定时使用的负载因子。
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * An empty table instance to share when the table is not inflated.
     */
    // 表未膨胀时共享的空表实例。
    static final Entry<?,?>[] EMPTY_TABLE = {};

    /**
     * The table, resized as necessary. Length MUST Always be a power of two.
     */
    // 表格，根据需要调整大小。 长度必须始终是 2 的幂。
    transient Entry<K,V>[] table = (Entry<K,V>[]) EMPTY_TABLE;

    /**
     * The number of key-value mappings contained in this map.
     */
    // 此映射中包含的键值映射数。
    transient int size;

    /**
     * The next size value at which to resize (capacity * load factor).
     * @serial
     */
    // If table == EMPTY_TABLE then this is the initial capacity at which the table will be created when inflated.
    // 要调整大小的下一个大小值（容量 * 负载因子）。如果 table == EMPTY_TABLE 那么这是膨胀时将创建表的初始容量。
    int threshold;

    /**
     * The load factor for the hash table.
     *
     * @serial
     */
    // 哈希表的负载因子。
    final float loadFactor;

    /**
     * 20210628
     * 此HashMap被结构修改的次数结构修改是更改 HashMap 中的映射数量或以其他方式修改其内部结构（例如，重新散列）。
     * 该字段用于在HashMap的Collection-views上创建迭代器快速失败。（请参阅 ConcurrentModificationException）。
     */
    /**
     * The number of times this HashMap has been structurally modified
     * Structural modifications are those that change the number of mappings in
     * the HashMap or otherwise modify its internal structure (e.g.,
     * rehash).  This field is used to make iterators on Collection-views of
     * the HashMap fail-fast.  (See ConcurrentModificationException).
     */
    transient int modCount;

    /**
     * 20210628
     * A. 映射容量的默认阈值，高于该阈值的替代散列用于字符串键。 由于字符串键的哈希码计算较弱，替代哈希减少了冲突的发生率。
     * B. 可以通过定义系统属性 {@code jdk.map.althashing.threshold} 来覆盖此值。 {@code 1} 的属性值强制始终使用替代散列，
     *    而{@code -1} 值确保永远不会使用替代散列。
     */
    /**
     * A.
     * The default threshold of map capacity above which alternative hashing is
     * used for String keys. Alternative hashing reduces the incidence of
     * collisions due to weak hash code calculation for String keys.
     *
     * B.
     * <p/>
     * This value may be overridden by defining the system property
     * {@code jdk.map.althashing.threshold}. A property value of {@code 1}
     * forces alternative hashing to be used at all times whereas
     * {@code -1} value ensures that alternative hashing is never used.
     */
    // 哈希种子阈值默认值
    static final int ALTERNATIVE_HASHING_THRESHOLD_DEFAULT = Integer.MAX_VALUE;

    /**
     * holds values which can't be initialized until after VM is booted.
     */
    // 保存直到VM 启动后才能初始化的值。
    private static class Holder {

        /**
         * Table capacity above which to switch to use alternative hashing.
         */
        // 哈希种子阈值, 默认为Integer.MAX_VALUE, 或者为jdk.map.althashing.threshold指定的值
        static final int ALTERNATIVE_HASHING_THRESHOLD;

        static {
            // 取当前虚拟机的环境变量阈值-Djdk.map.althashing.threshold
            String altThreshold = java.security.AccessController.doPrivileged(
                new sun.security.action.GetPropertyAction(
                    "jdk.map.althashing.threshold"));

            // 如果指定了-Djdk.map.althashing.threshold, 则threshold为指定的阈值, 否则threshold为Integer.MAX_VALUE
            int threshold;
            try {
                threshold = (null != altThreshold)
                        ? Integer.parseInt(altThreshold)
                        : ALTERNATIVE_HASHING_THRESHOLD_DEFAULT;

                // disable alternative hashing if -1
                if (threshold == -1) {
                    threshold = Integer.MAX_VALUE;
                }

                if (threshold < 0) {
                    throw new IllegalArgumentException("value must be positive integer.");
                }
            } catch(IllegalArgumentException failed) {
                throw new Error("Illegal value for 'jdk.map.althashing.threshold'", failed);
            }

            // 最后更新ALTERNATIVE_HASHING_THRESHOLD为threshold, 默认为Integer.MAX_VALUE
            ALTERNATIVE_HASHING_THRESHOLD = threshold;
        }
    }

    /**
     * A randomizing value associated with this instance that is applied to
     * hash code of keys to make hash collisions harder to find. If 0 then
     * alternative hashing is disabled.
     */
    // 与此实例关联的随机值，应用于键的哈希码，使哈希冲突更难找到。如果为 0，则禁用替代散列。
    transient int hashSeed = 0;

    /**
     * Constructs an empty <tt>HashMap</tt> with the specified initial
     * capacity and load factor.
     *
     * @param  initialCapacity the initial capacity
     * @param  loadFactor      the load factor
     * @throws IllegalArgumentException if the initial capacity is negative
     *         or the load factor is nonpositive
     */
    // 构造一个具有指定初始容量和负载因子的空HashMap
    public HashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " +
                                               initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " +
                                               loadFactor);

        this.loadFactor = loadFactor;
        threshold = initialCapacity;
        init();
    }

    /**
     * Constructs an empty <tt>HashMap</tt> with the specified initial
     * capacity and the default load factor (0.75).
     *
     * @param  initialCapacity the initial capacity.
     * @throws IllegalArgumentException if the initial capacity is negative.
     */
    // 构造一个具有指定初始容量和默认负载因子 (0.75) 的空 HashMap。
    public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    /**
     * Constructs an empty <tt>HashMap</tt> with the default initial capacity
     * (16) and the default load factor (0.75).
     */
    // 构造一个具有默认初始容量 (16) 和默认负载因子 (0.75) 的空 HashMap。
    public HashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    /**
     * Constructs a new <tt>HashMap</tt> with the same mappings as the
     * specified <tt>Map</tt>.  The <tt>HashMap</tt> is created with
     * default load factor (0.75) and an initial capacity sufficient to
     * hold the mappings in the specified <tt>Map</tt>.
     *
     * @param   m the map whose mappings are to be placed in this map
     * @throws  NullPointerException if the specified map is null
     */
    // 构造一个与指定Map具有相同映射关系的新HashMap。 HashMap 是使用默认负载因子(0.75)和足以在指定Map中保存映射的初始容量创建的。
    public HashMap(Map<? extends K, ? extends V> m) {
        // 使用默认容量16或者根据复制集合大小计算的容量, 以及默认负载因子0.75构造HashMap, 其中由于table=[], 所以使用指定的容量作为阈值
        this(Math.max((int) (m.size() / DEFAULT_LOAD_FACTOR) + 1,
                      DEFAULT_INITIAL_CAPACITY), DEFAULT_LOAD_FACTOR);

        // 根据指定容量初始化散列表(只在散列表表为空时调用)，根据规范化后的容量计算新阈值以及构造新容量的散列表
        inflateTable(threshold);

        // 添加复制集合所有元素, 但不会调整散列表的大小
        putAllForCreate(m);
    }

    // 返回给定目标容量的2的幂次, 规范化容量: 取2的(number - 1) <<< 1最高位幂次容量
    private static int roundUpToPowerOf2(int number) {
        // assert number >= 0 : "number must be non-negative"; “数字必须是非负数”；
        return number >= MAXIMUM_CAPACITY
                ? MAXIMUM_CAPACITY
                // 返回最高位为1其他位补0的数值
                : (number > 1) ? Integer.highestOneBit((number - 1) << 1) : 1;
    }

    /**
     * Inflates the table.
     */
    // 根据指定容量初始化散列表(只在散列表表为空时调用)，根据规范化后的容量计算新阈值以及构造新容量的散列表
    private void inflateTable(int toSize) {
        // Find a power of 2 >= toSize // 求 2 的幂 >= toSize
        // 返回给定目标容量的2的幂次, 规范化容量: 取2的(number - 1) <<< 1最高位幂次容量
        int capacity = roundUpToPowerOf2(toSize);

        // 根据规范化后的容量计算新阈值
        threshold = (int) Math.min(capacity * loadFactor, MAXIMUM_CAPACITY + 1);

        // 构造新容量的散列表
        table = new Entry[capacity];

        // 生成/切换哈希种子(会推迟初始化, 默认为0, 代表不生效), 直到指定的容量大于哈希种子阈值(可通过改变JVM参数提前触发更换时机), 返回值用作判断是否重新hash的依据(因为返回true代表了更换了哈希算法), 一般为false
        initHashSeedAsNeeded(capacity);
    }

    // internal utilities

    /**
     * 20210628
     * 子类的初始化挂钩。在初始化HashMap之后但在插入任何条目之前，在所有构造函数和伪构造函数（clone、readObject）中调用此方法。
     * （如果没有这个方法，readObject将需要子类的显式知识。）
     */
    /**
     * Initialization hook for subclasses. This method is called
     * in all constructors and pseudo-constructors (clone, readObject)
     * after HashMap has been initialized but before any entries have
     * been inserted.  (In the absence of this method, readObject would
     * require explicit knowledge of subclasses.)
     */
    void init() {
    }

    /**
     * Initialize the hashing mask value. We defer initialization until we
     * really need it.
     */
    // 生成/切换哈希种子(会推迟初始化, 默认为0, 代表不生效), 直到指定的容量大于哈希种子阈值(可通过改变JVM参数提前触发更换时机), 返回值用作判断是否重新hash的依据(因为返回true代表了更换了哈希算法), 一般为false
    final boolean initHashSeedAsNeeded(int capacity) {
        // 当前是否存在哈希种子，默认为0 => 即currentAltHashing默认为0
        boolean currentAltHashing = hashSeed != 0;

        // 如果JVM在运行, 且指定的容量大于哈希种子阈值, 说明认为当前哈希算法不太均匀, 则需要生成/切换哈希种子, 从而更换哈希算法
        boolean useAltHashing = sun.misc.VM.isBooted() && (capacity >= Holder.ALTERNATIVE_HASHING_THRESHOLD);

        // 如果currentAltHashing与useAltHashing不相等, 则返回true, 代表需要生成/切换哈希种子
        boolean switching = currentAltHashing ^ useAltHashing;
        if (switching) {
            // 返回一个非零的32位伪随机值, 其中当前HashMap实例用作值的一部分, 构造新的哈希种子作为掩码
            hashSeed = useAltHashing ? sun.misc.Hashing.randomHashSeed(this) : 0;
        }

        // 返回是否生成/切换哈希种子
        return switching;
    }

    /**
     * 20210628
     * 检索对象散列代码并将补充散列函数应用于结果散列，以防止质量差的散列函数。 这很关键，因为 HashMap 使用长度为 2 的幂的哈希表，
     * 否则会遇到低位没有不同的 hashCode 的冲突。 注意：null键总是映射到哈希 0，因此索引 0。
     */
    /**
     * Retrieve object hash code and applies a supplemental hash function to the
     * result hash, which defends against poor quality hash functions.  This is
     * critical because HashMap uses power-of-two length hash tables, that
     * otherwise encounter collisions for hashCodes that do not differ
     * in lower bits. Note: Null keys always map to hash 0, thus index 0.
     */
    // HashCode扰动函数, key为String类型则返回key的32位哈希码即可, 否则哈希种子异或k的散列码, 再把结果高位的特征和低位的特征组合起来，降低哈希冲突的概率，也就是说，尽量做到任何一位的变化都能对最终得到的结果产生影响
    final int hash(Object k) {
        int h = hashSeed;

        // 如果k为String类型, 则返回k的32位哈希值
        if (0 != h && k instanceof String) {
            return sun.misc.Hashing.stringHash32((String) k);
        }

        // 如果k不为String类型, 则哈希种子异或k的散列码, 再把结果高位的特征和低位的特征组合起来，降低哈希冲突的概率，也就是说，尽量做到任何一位的变化都能对最终得到的结果产生影响
        // 如果h为默认0, 则h异或k的散列表码, 还是会等于k的散列码
        h ^= k.hashCode();

        // 此函数可确保在每个位位置仅相差常数倍的 hashCode 具有有限数量的冲突（在默认加载因子下约为 8）。
        // This function ensures that hashCodes that differ only by
        // constant multiples at each bit position have a bounded
        // number of collisions (approximately 8 at default load factor).
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    /**
     * Returns index for hash code h.
     */
    // 返回哈希码 h 的索引。
    static int indexFor(int h, int length) {
        // assert Integer.bitCount(length) == 1 : "length must be a non-zero power of 2"; // “长度必须是 2 的非零次方”；
        // 如果n为2的幂次数, 则n-1可以得到散列表容量n的掩码, 0~n-1表示散列表的下标范围, h & (n-1)相当于h % n, 因此这就是n为2的幂次的原因1, 因此与操作比取与操作快
        return h & (length-1);
    }

    /**
     * Returns the number of key-value mappings in this map.
     *
     * @return the number of key-value mappings in this map
     */
    public int size() {
        return size;
    }

    /**
     * Returns <tt>true</tt> if this map contains no key-value mappings.
     *
     * @return <tt>true</tt> if this map contains no key-value mappings
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 20210630
     * A. 返回指定键映射到的值，如果此映射不包含键的映射，则返回 {@code null}。
     * B. 更正式地说，如果此映射包含从键 {@code k} 到值 {@code v} 的映射，使得 {@code (key==null ? k==null : key.equals(k))}，
     *    然后这个方法返回 {@code v}; 否则返回 {@code null}。 （最多可以有一个这样的映射。）
     * C. {@code null} 的返回值不一定表示映射不包含键的映射； Map也有可能将键显式映射到 {@code null}。 {@link #containsKey containsKey} 操作可用于区分这两种情况。
     */
    /**
     * A.
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * B.
     * <p>More formally, if this map contains a mapping from a key
     * {@code k} to a value {@code v} such that {@code (key==null ? k==null :
     * key.equals(k))}, then this method returns {@code v}; otherwise
     * it returns {@code null}.  (There can be at most one such mapping.)
     *
     * C.
     * <p>A return value of {@code null} does not <i>necessarily</i>
     * indicate that the map contains no mapping for the key; it's also
     * possible that the map explicitly maps the key to {@code null}.
     * The {@link #containsKey containsKey} operation may be used to
     * distinguish these two cases.
     *
     * @see #put(Object, Object)
     */
    // 返回指定键映射到的值，如果此映射不包含键的映射，则返回 {@code null}
    public V get(Object key) {
        // 如果key为null, 则从第1个桶结点获取null键以及对应的value值, 第1个桶结点e, 如果e键为null, 则返回e值, 否则返回null
        if (key == null)
            return getForNullKey();

        // 如果key不为null, 返回与 HashMap 中指定键关联的条目。 如果 HashMap 不包含键的映射，则返回 null
        Entry<K,V> entry = getEntry(key);
        return null == entry ? null : entry.getValue();
    }

    /**
     * 20210630
     * 卸载版本的 get() 以查找空键。 null映射到索引 0。为了提高两个最常用操作（get 和 put）的性能，这种空情况被拆分为单独的方法，但在其他操作中与条件合并。
     */
    /**
     * Offloaded version of get() to look up null keys.  Null keys map
     * to index 0.  This null case is split out into separate methods
     * for the sake of performance in the two most commonly used
     * operations (get and put), but incorporated with conditionals in
     * others.
     */
    // 从第1个桶结点获取null键以及对应的value值, 第1个桶结点e, 如果e键为null, 则返回e值, 否则返回null
    private V getForNullKey() {
        if (size == 0) {
            return null;
        }

        // 第1个桶结点e, 如果e键为null, 则返回e值, 否则返回null
        for (Entry<K,V> e = table[0]; e != null; e = e.next) {
            if (e.key == null)
                return e.value;
        }
        return null;
    }

    /**
     * Returns <tt>true</tt> if this map contains a mapping for the
     * specified key.
     *
     * @param   key   The key whose presence in this map is to be tested
     * @return <tt>true</tt> if this map contains a mapping for the specified
     * key.
     */
    public boolean containsKey(Object key) {
        return getEntry(key) != null;
    }

    /**
     * Returns the entry associated with the specified key in the
     * HashMap.  Returns null if the HashMap contains no mapping
     * for the key.
     */
    // 返回与 HashMap 中指定键关联的条目。 如果 HashMap 不包含键的映射，则返回 null。
    final Entry<K,V> getEntry(Object key) {
        if (size == 0) {
            return null;
        }

        // HashCode扰动函数, key为String类型则返回key的32位哈希码即可, 否则哈希种子异或k的散列码, 再把结果高位的特征和低位的特征组合起来，降低哈希冲突的概率，也就是说，尽量做到任何一位的变化都能对最终得到的结果产生影响
        int hash = (key == null) ? 0 : hash(key);

        // 返回哈希码 h 的索引, 获取该索引所在的桶结点e, 遍历e链表
        for (Entry<K,V> e = table[indexFor(hash, table.length)]; e != null; e = e.next) {
            Object k;

            // 如果e的hash等于key的hash值, 且e键等于key或者e键equals key, 说明e就是要找的结点, 则返回e结点即可
            if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
                return e;
        }

        // 如果确实找不到e结点, 则返回null
        return null;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old
     * value is replaced.
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with <tt>key</tt>, or
     *         <tt>null</tt> if there was no mapping for <tt>key</tt>.
     *         (A <tt>null</tt> return can also indicate that the map
     *         previously associated <tt>null</tt> with <tt>key</tt>.)
     */
    // 将指定值与此映射中的指定键相关联, 如果映射先前包含键的映射，则旧值将被替换
    public V put(K key, V value) {
        // 如果散列表为空, 则根据指定容量初始化散列表(只在散列表表为空时调用)，根据规范化后的容量计算新阈值以及构造新容量的散列表
        if (table == EMPTY_TABLE) {
            inflateTable(threshold);
        }

        // 如果key为null, 则将null键、value值添加到第1个桶, 其中底层会调整散列表大小
        if (key == null)
            return putForNullKey(value);

        // HashCode扰动函数, key为String类型则返回key的32位哈希码即可, 否则哈希种子异或k的散列码, 再把结果高位的特征和低位的特征组合起来，降低哈希冲突的概率，也就是说，尽量做到任何一位的变化都能对最终得到的结果产生影响
        int hash = hash(key);

        // 返回哈希码 h 的索引
        int i = indexFor(hash, table.length);

        // 从i桶开始遍历链表, 当前遍历结点e, e键k
        for (Entry<K,V> e = table[i]; e != null; e = e.next) {
            Object k;

            // 如果e的hash值等于key的hash值, 且e键等于key或者e键equalskey, 说明e结点就是要找的结点, 则替换e值并返回旧值
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                V oldValue = e.value;
                e.value = value;
                e.recordAccess(this);// LinkedHashMap值覆盖回调方法

                // 替换成功, 返回旧值
                return oldValue;
            }
        }

        modCount++;

        // 将具有指定键、值和哈希码的新条目添加到指定存储桶， 其中此方法会负责调整散列表大小
        addEntry(hash, key, value, i);

        // 添加成功, 返回null
        return null;
    }

    /**
     * Offloaded version of put for null keys
     */
    // 将null键、value值添加到第1个桶, 其中底层会调整散列表大小
    private V putForNullKey(V value) {
        // 从第1个桶开始遍历链表, 当前遍历结点e
        for (Entry<K,V> e = table[0]; e != null; e = e.next) {
            // 如果e键确实为null, 说明e结点就是要找的结点, 则替换e值并返回旧值
            if (e.key == null) {
                V oldValue = e.value;
                e.value = value;
                e.recordAccess(this);// LinkedHashMap值覆盖回调方法

                // 替换成功, 返回旧值
                return oldValue;
            }
        }
        modCount++;

        // 将null键、value值添加到第1个桶， 其中此方法会负责调整散列表大小
        addEntry(0, null, value, 0);

        // 添加成功, 返回null
        return null;
    }

    /**
     * 20210628
     * 构造函数和伪构造函数（clone、readObject）使用此方法而不是放置。 它不会调整表的大小、检查共同修改等。它调用 createEntry 而不是 addEntry。
     */
    /**
     * This method is used instead of put by constructors and
     * pseudoconstructors (clone, readObject).  It does not resize the table,
     * check for comodification, etc.  It calls createEntry rather than
     * addEntry.
     */
    // 添加指定key、value元素, 但不会调整散列表的大小
    private void putForCreate(K key, V value) {
        int hash = null == key ? 0 : hash(key);
        int i = indexFor(hash, table.length);

        /**
         * Look for preexisting entry for key.  This will never happen for
         * clone or deserialize.  It will only happen for construction if the
         * input Map is a sorted map whose ordering is inconsistent w/ equals.
         */
        for (Entry<K,V> e = table[i]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash &&
                ((k = e.key) == key || (key != null && key.equals(k)))) {
                e.value = value;
                return;
            }
        }

        // 使用hash值以及哈希索引, 在散列表中创建条目, 不会调整散列表大小
        createEntry(hash, key, value, i);
    }

    // 添加复制集合所有元素, 但不会调整散列表的大小
    private void putAllForCreate(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet())
            // 添加指定key、value元素, 但不会调整散列表的大小
            putForCreate(e.getKey(), e.getValue());
    }

    /**
     * 20210628
     * A. 将此映射的内容重新散列到具有更大容量的新数组中。 当此映射中的键数达到其阈值时，将自动调用此方法。
     * B. 如果当前容量为 MAXIMUM_CAPACITY，则此方法不会调整Map大小，而是将阈值设置为 Integer.MAX_VALUE。 这具有防止将来调用的效果。
     */
    /**
     * A.
     * Rehashes the contents of this map into a new array with a
     * larger capacity.  This method is called automatically when the
     * number of keys in this map reaches its threshold.
     *
     * B.
     * If current capacity is MAXIMUM_CAPACITY, this method does not
     * resize the map, but sets threshold to Integer.MAX_VALUE.
     * This has the effect of preventing future calls.
     *
     * @param newCapacity the new capacity, MUST be a power of two;
     *        must be greater than current capacity unless current
     *        capacity is MAXIMUM_CAPACITY (in which case value
     *        is irrelevant).
     */
    // 根据指定容量扩容散列表(当实际大小超过阈值时调用), 使用头插法将当前表中的所有条目传输到newTable, 会重新计算每个结点的hash值以及新的阈值
    void resize(int newCapacity) {
        Entry[] oldTable = table;
        int oldCapacity = oldTable.length;
        if (oldCapacity == MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return;
        }

        Entry[] newTable = new Entry[newCapacity];

        // 使用头插法将当前表中的所有条目传输到newTable, 可能会重新计算hash值
        transfer(newTable,
                // 生成/切换哈希种子(会推迟初始化, 默认为0, 代表不生效), 直到指定的容量大于哈希种子阈值(可通过改变JVM参数提前触发更换时机), 返回值用作判断是否重新hash的依据(因为返回true代表了更换了哈希算法), 一般为false
                initHashSeedAsNeeded(newCapacity));

        table = newTable;
        threshold = (int)Math.min(newCapacity * loadFactor, MAXIMUM_CAPACITY + 1);
    }

    /**
     * Transfers all entries from current table to newTable.
     */
    // 使用头插法将当前表中的所有条目传输到newTable, 可能会重新计算hash值
    void transfer(Entry[] newTable, boolean rehash) {
        int newCapacity = newTable.length;

        // 遍历散列表, 当前遍历到的桶e
        for (Entry<K,V> e : table) {

            // 遍历e链表, 如果哈希种子有变化, 则重新每个e结点的hash值, 重新计算哈希索引i, 并设置到新散列表i桶中
            while(null != e) {
                Entry<K,V> next = e.next;
                if (rehash) {
                    e.hash = null == e.key ? 0 : hash(e.key);
                }
                int i = indexFor(e.hash, newCapacity);

                // 头插法
                e.next = newTable[i];
                newTable[i] = e;

                // 继续遍历e链表
                e = next;
            }
        }
    }

    /**
     * 20210629
     * 将所有映射从指定映射复制到此映射。 这些映射将替换此映射对当前在指定映射中的任何键的任何映射。
     */
    /**
     * Copies all of the mappings from the specified map to this map.
     * These mappings will replace any mappings that this map had for
     * any of the keys currently in the specified map.
     *
     * @param m mappings to be stored in this map
     * @throws NullPointerException if the specified map is null
     */
    public void putAll(Map<? extends K, ? extends V> m) {
        int numKeysToBeAdded = m.size();
        if (numKeysToBeAdded == 0)
            return;

        // 如果散列表为空, 则根据计算出来的阈值初始化散列表(只在散列表表为空时调用)，根据规范化后的容量计算新阈值以及构造新容量的散列表
        if (table == EMPTY_TABLE) {
            inflateTable((int) Math.max(numKeysToBeAdded * loadFactor, threshold));
        }

        /**
         * 20210629
         * 如果要添加的映射数大于或等于阈值，则扩展映射。这是保守的；显而易见的条件是 (m.size() + size) >= threshold，但如果要添加的键与此映射中已有的键重叠，
         * 则此条件可能导致映射具有两倍的适当容量。通过使用保守计算，我们最多对自己进行一次额外的调整。
         */
        /*
         * Expand the map if the map if the number of mappings to be added
         * is greater than or equal to threshold.  This is conservative; the
         * obvious condition is (m.size() + size) >= threshold, but this
         * condition could result in a map with twice the appropriate capacity,
         * if the keys to be added overlap with the keys already in this map.
         * By using the conservative calculation, we subject ourself
         * to at most one extra resize.
         */
        // 如果复制集合的大小大于当前阈值, 则先计算新的容量(最接近targetCapacity的2^n), 而targetCapacity=复制集合实际大小 * 负载因子
        if (numKeysToBeAdded > threshold) {
            int targetCapacity = (int)(numKeysToBeAdded / loadFactor + 1);
            if (targetCapacity > MAXIMUM_CAPACITY)
                targetCapacity = MAXIMUM_CAPACITY;

            // 扩容到接近当前容量的2^
            int newCapacity = table.length;
            while (newCapacity < targetCapacity)
                newCapacity <<= 1;

            // 根据指定容量扩容散列表(当实际大小超过阈值时调用), 使用头插法将当前表中的所有条目传输到newTable, 会重新计算每个结点的hash值以及新的阈值
            if (newCapacity > table.length)
                resize(newCapacity);
        }

        // 遍历指定复制集合所有的条目, 并添加到WeakHashMap的散列表中
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet())
            put(e.getKey(), e.getValue());
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     *
     * @param  key key whose mapping is to be removed from the map
     * @return the previous value associated with <tt>key</tt>, or
     *         <tt>null</tt> if there was no mapping for <tt>key</tt>.
     *         (A <tt>null</tt> return can also indicate that the map
     *         previously associated <tt>null</tt> with <tt>key</tt>.)
     */
    // 如果存在指定的键, 则从此映射中删除指定键的映射
    public V remove(Object key) {
        // 移除并返回与HashMap中指定键关联的条目, 如果HashMap不包含此键的映射, 则返回 null
        Entry<K,V> e = removeEntryForKey(key);
        return (e == null ? null : e.value);
    }

    /**
     * Removes and returns the entry associated with the specified key
     * in the HashMap.  Returns null if the HashMap contains no mapping
     * for this key.
     */
    // 移除并返回与HashMap中指定键关联的条目, 如果HashMap不包含此键的映射, 则返回 null
    final Entry<K,V> removeEntryForKey(Object key) {
        if (size == 0) {
            return null;
        }

        // HashCode扰动函数, key为String类型则返回key的32位哈希码即可, 否则哈希种子异或k的散列码, 再把结果高位的特征和低位的特征组合起来，降低哈希冲突的概率，也就是说，尽量做到任何一位的变化都能对最终得到的结果产生影响
        int hash = (key == null) ? 0 : hash(key);

        // 返回哈希码h的索引i, 获取i桶结点e、前驱prev(初始时为e)
        int i = indexFor(hash, table.length);
        Entry<K,V> prev = table[i];
        Entry<K,V> e = prev;

        // 遍历e链表, 后继next, e键k
        while (e != null) {
            Entry<K,V> next = e.next;
            Object k;

            // 如果e的hash值等于key的hash值, 且e键等于key或者e键equals key, 则说明找到了结点e
            if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
                modCount++;
                size--;

                // 如果prev等于e, 说明要删除的结点正好为桶头结点, 则替换next为新的桶头
                if (prev == e)
                    table[i] = next;
                // 如果prev不等于e, 说明要删除的结点不为桶头结点, 则脱钩e结点
                else
                    prev.next = next;

                // LinkedHashMap结点删除回调方法
                e.recordRemoval(this);

                // 删除成功, 则返回e结点
                return e;
            }

            // 如果还没找到要删除的结点e, 则继续遍历e链表
            prev = e;
            e = next;
        }

        // 如果最后还没找到e结点, 则返回null
        return e;
    }

    /**
     * Special version of remove for EntrySet using {@code Map.Entry.equals()}
     * for matching.
     */
    final Entry<K,V> removeMapping(Object o) {
        if (size == 0 || !(o instanceof Map.Entry))
            return null;

        Map.Entry<K,V> entry = (Map.Entry<K,V>) o;
        Object key = entry.getKey();
        int hash = (key == null) ? 0 : hash(key);
        int i = indexFor(hash, table.length);
        Entry<K,V> prev = table[i];
        Entry<K,V> e = prev;

        while (e != null) {
            Entry<K,V> next = e.next;
            if (e.hash == hash && e.equals(entry)) {
                modCount++;
                size--;
                if (prev == e)
                    table[i] = next;
                else
                    prev.next = next;
                e.recordRemoval(this);
                return e;
            }
            prev = e;
            e = next;
        }

        return e;
    }

    /**
     * Removes all of the mappings from this map.
     * The map will be empty after this call returns.
     */
    public void clear() {
        modCount++;
        Arrays.fill(table, null);
        size = 0;
    }

    /**
     * Returns <tt>true</tt> if this map maps one or more keys to the
     * specified value.
     *
     * @param value value whose presence in this map is to be tested
     * @return <tt>true</tt> if this map maps one or more keys to the
     *         specified value
     */
    public boolean containsValue(Object value) {
        if (value == null)
            return containsNullValue();

        Entry[] tab = table;
        for (int i = 0; i < tab.length ; i++)
            for (Entry e = tab[i] ; e != null ; e = e.next)
                if (value.equals(e.value))
                    return true;
        return false;
    }

    /**
     * Special-case code for containsValue with null argument
     */
    private boolean containsNullValue() {
        Entry[] tab = table;
        for (int i = 0; i < tab.length ; i++)
            for (Entry e = tab[i] ; e != null ; e = e.next)
                if (e.value == null)
                    return true;
        return false;
    }

    /**
     * Returns a shallow copy of this <tt>HashMap</tt> instance: the keys and
     * values themselves are not cloned.
     *
     * @return a shallow copy of this map
     */
    public Object clone() {
        HashMap<K,V> result = null;
        try {
            result = (HashMap<K,V>)super.clone();
        } catch (CloneNotSupportedException e) {
            // assert false;
        }
        if (result.table != EMPTY_TABLE) {
            result.inflateTable(Math.min(
                (int) Math.min(
                    size * Math.min(1 / loadFactor, 4.0f),
                    // we have limits...
                    HashMap.MAXIMUM_CAPACITY),
               table.length));
        }
        result.entrySet = null;
        result.modCount = 0;
        result.size = 0;
        result.init();
        result.putAllForCreate(this);

        return result;
    }

    static class Entry<K,V> implements Map.Entry<K,V> {
        final K key;
        V value;
        Entry<K,V> next;
        int hash;

        /**
         * Creates new entry.
         */
        Entry(int h, K k, V v, Entry<K,V> n) {
            value = v;
            next = n;
            key = k;
            hash = h;
        }

        public final K getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (!(o instanceof Map.Entry))
                return false;
            Map.Entry e = (Map.Entry)o;
            Object k1 = getKey();
            Object k2 = e.getKey();
            if (k1 == k2 || (k1 != null && k1.equals(k2))) {
                Object v1 = getValue();
                Object v2 = e.getValue();
                if (v1 == v2 || (v1 != null && v1.equals(v2)))
                    return true;
            }
            return false;
        }

        public final int hashCode() {
            return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());
        }

        public final String toString() {
            return getKey() + "=" + getValue();
        }

        /**
         * This method is invoked whenever the value in an entry is
         * overwritten by an invocation of put(k,v) for a key k that's already
         * in the HashMap.
         */
        // 每当条目中的值被 HashMap 中已经存在的键 k 的 put(k,v) 调用覆盖时，就会调用此方法。
        void recordAccess(HashMap<K,V> m) {
        }

        /**
         * This method is invoked whenever the entry is
         * removed from the table.
         */
        // 每当从表中删除条目时都会调用此方法
        void recordRemoval(HashMap<K,V> m) {
        }
    }

    /**
     * 20210628
     * A. 将具有指定键、值和哈希码的新条目添加到指定存储桶。 如果合适，此方法负责调整表的大小。
     * B. 子类覆盖它以改变 put 方法的行为。
     */
    /**
     * A.
     * Adds a new entry with the specified key, value and hash code to
     * the specified bucket.  It is the responsibility of this
     * method to resize the table if appropriate.
     *
     * B.
     * Subclass overrides this to alter the behavior of put method.
     */
    // 将具有指定键、值和哈希码的新条目添加到指定存储桶， 其中此方法会负责调整散列表大小
    void addEntry(int hash, K key, V value, int bucketIndex) {
        // 如果实例大小大于当前阈值, 且bucketIndex桶结点不为null, 说明HashMap需要扩容了
        if ((size >= threshold) && (null != table[bucketIndex])) {
            // 根据指定容量扩容散列表(当实际大小超过阈值时调用), 使用头插法将当前表中的所有条目传输到newTable, 会重新计算每个结点的hash值以及新的阈值
            resize(2 * table.length);

            // 重新计算hash值, 以及哈希索引
            hash = (null != key) ? hash(key) : 0;
            bucketIndex = indexFor(hash, table.length);
        }

        // 添加复制集合所有元素, 但不会调整散列表的大小
        createEntry(hash, key, value, bucketIndex);
    }

    /**
     * 20210628
     * A. 与 addEntry 类似，只是在创建条目作为 Map 构造或“伪构造”（克隆、反序列化）的一部分时使用此版本。 此版本无需担心调整表格大小。
     * B. 子类覆盖它以改变 HashMap(Map)、clone 和 readObject 的行为。
     */
    /**
     * A.
     * Like addEntry except that this version is used when creating entries
     * as part of Map construction or "pseudo-construction" (cloning,
     * deserialization).  This version needn't worry about resizing the table.
     *
     * B.
     * Subclass overrides this to alter the behavior of HashMap(Map),
     * clone, and readObject.
     */
    // 使用hash值以及哈希索引, 在散列表中创建条目, 不会调整散列表大小
    void createEntry(int hash, K key, V value, int bucketIndex) {
        Entry<K,V> e = table[bucketIndex];
        table[bucketIndex] = new Entry<>(hash, key, value, e);
        size++;
    }

    private abstract class HashIterator<E> implements Iterator<E> {
        Entry<K,V> next;        // next entry to return 下一个返回的条目
        int expectedModCount;   // For fast-fail 对于快速失败
        int index;              // current slot 当前插槽
        Entry<K,V> current;     // current entry 当前条目

        HashIterator() {
            expectedModCount = modCount;
            if (size > 0) { // advance to first entry
                Entry[] t = table;
                while (index < t.length && (next = t[index++]) == null)
                    ;
            }
        }

        public final boolean hasNext() {
            return next != null;
        }

        final Entry<K,V> nextEntry() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            Entry<K,V> e = next;
            if (e == null)
                throw new NoSuchElementException();

            if ((next = e.next) == null) {
                Entry[] t = table;
                while (index < t.length && (next = t[index++]) == null)
                    ;
            }
            current = e;
            return e;
        }

        public void remove() {
            if (current == null)
                throw new IllegalStateException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            Object k = current.key;
            current = null;
            HashMap.this.removeEntryForKey(k);
            expectedModCount = modCount;
        }
    }

    private final class ValueIterator extends HashIterator<V> {
        public V next() {
            return nextEntry().value;
        }
    }

    private final class KeyIterator extends HashIterator<K> {
        public K next() {
            return nextEntry().getKey();
        }
    }

    private final class EntryIterator extends HashIterator<Map.Entry<K,V>> {
        public Map.Entry<K,V> next() {
            return nextEntry();
        }
    }

    // Subclass overrides these to alter behavior of views' iterator() method
    Iterator<K> newKeyIterator()   {
        return new KeyIterator();
    }
    Iterator<V> newValueIterator()   {
        return new ValueIterator();
    }
    Iterator<Map.Entry<K,V>> newEntryIterator()   {
        return new EntryIterator();
    }


    // Views

    private transient Set<Map.Entry<K,V>> entrySet = null;

    /**
     * Returns a {@link Set} view of the keys contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  If the map is modified
     * while an iteration over the set is in progress (except through
     * the iterator's own <tt>remove</tt> operation), the results of
     * the iteration are undefined.  The set supports element removal,
     * which removes the corresponding mapping from the map, via the
     * <tt>Iterator.remove</tt>, <tt>Set.remove</tt>,
     * <tt>removeAll</tt>, <tt>retainAll</tt>, and <tt>clear</tt>
     * operations.  It does not support the <tt>add</tt> or <tt>addAll</tt>
     * operations.
     */
    public Set<K> keySet() {
        Set<K> ks = keySet;
        return (ks != null ? ks : (keySet = new KeySet()));
    }

    private final class KeySet extends AbstractSet<K> {
        public Iterator<K> iterator() {
            return newKeyIterator();
        }
        public int size() {
            return size;
        }
        public boolean contains(Object o) {
            return containsKey(o);
        }
        public boolean remove(Object o) {
            return HashMap.this.removeEntryForKey(o) != null;
        }
        public void clear() {
            HashMap.this.clear();
        }
    }

    /**
     * Returns a {@link Collection} view of the values contained in this map.
     * The collection is backed by the map, so changes to the map are
     * reflected in the collection, and vice-versa.  If the map is
     * modified while an iteration over the collection is in progress
     * (except through the iterator's own <tt>remove</tt> operation),
     * the results of the iteration are undefined.  The collection
     * supports element removal, which removes the corresponding
     * mapping from the map, via the <tt>Iterator.remove</tt>,
     * <tt>Collection.remove</tt>, <tt>removeAll</tt>,
     * <tt>retainAll</tt> and <tt>clear</tt> operations.  It does not
     * support the <tt>add</tt> or <tt>addAll</tt> operations.
     */
    public Collection<V> values() {
        Collection<V> vs = values;
        return (vs != null ? vs : (values = new Values()));
    }

    private final class Values extends AbstractCollection<V> {
        public Iterator<V> iterator() {
            return newValueIterator();
        }
        public int size() {
            return size;
        }
        public boolean contains(Object o) {
            return containsValue(o);
        }
        public void clear() {
            HashMap.this.clear();
        }
    }

    /**
     * Returns a {@link Set} view of the mappings contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  If the map is modified
     * while an iteration over the set is in progress (except through
     * the iterator's own <tt>remove</tt> operation, or through the
     * <tt>setValue</tt> operation on a map entry returned by the
     * iterator) the results of the iteration are undefined.  The set
     * supports element removal, which removes the corresponding
     * mapping from the map, via the <tt>Iterator.remove</tt>,
     * <tt>Set.remove</tt>, <tt>removeAll</tt>, <tt>retainAll</tt> and
     * <tt>clear</tt> operations.  It does not support the
     * <tt>add</tt> or <tt>addAll</tt> operations.
     *
     * @return a set view of the mappings contained in this map
     */
    public Set<Map.Entry<K,V>> entrySet() {
        return entrySet0();
    }

    private Set<Map.Entry<K,V>> entrySet0() {
        Set<Map.Entry<K,V>> es = entrySet;
        return es != null ? es : (entrySet = new EntrySet());
    }

    private final class EntrySet extends AbstractSet<Map.Entry<K,V>> {
        public Iterator<Map.Entry<K,V>> iterator() {
            return newEntryIterator();
        }
        public boolean contains(Object o) {
            if (!(o instanceof Map.Entry))
                return false;
            Map.Entry<K,V> e = (Map.Entry<K,V>) o;
            Entry<K,V> candidate = getEntry(e.getKey());
            return candidate != null && candidate.equals(e);
        }
        public boolean remove(Object o) {
            return removeMapping(o) != null;
        }
        public int size() {
            return size;
        }
        public void clear() {
            HashMap.this.clear();
        }
    }

    /**
     * Save the state of the <tt>HashMap</tt> instance to a stream (i.e.,
     * serialize it).
     *
     * @serialData The <i>capacity</i> of the HashMap (the length of the
     *             bucket array) is emitted (int), followed by the
     *             <i>size</i> (an int, the number of key-value
     *             mappings), followed by the key (Object) and value (Object)
     *             for each key-value mapping.  The key-value mappings are
     *             emitted in no particular order.
     */
    private void writeObject(java.io.ObjectOutputStream s)
        throws IOException
    {
        // Write out the threshold, loadfactor, and any hidden stuff
        s.defaultWriteObject();

        // Write out number of buckets
        if (table==EMPTY_TABLE) {
            s.writeInt(roundUpToPowerOf2(threshold));
        } else {
           s.writeInt(table.length);
        }

        // Write out size (number of Mappings)
        s.writeInt(size);

        // Write out keys and values (alternating)
        if (size > 0) {
            for(Map.Entry<K,V> e : entrySet0()) {
                s.writeObject(e.getKey());
                s.writeObject(e.getValue());
            }
        }
    }

    private static final long serialVersionUID = 362498820763181265L;

    /**
     * Reconstitute the {@code HashMap} instance from a stream (i.e.,
     * deserialize it).
     */
    private void readObject(java.io.ObjectInputStream s)
         throws IOException, ClassNotFoundException
    {
        // Read in the threshold (ignored), loadfactor, and any hidden stuff
        s.defaultReadObject();
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new InvalidObjectException("Illegal load factor: " +
                                               loadFactor);
        }

        // set other fields that need values
        table = (Entry<K,V>[]) EMPTY_TABLE;

        // Read in number of buckets
        s.readInt(); // ignored.

        // Read number of mappings
        int mappings = s.readInt();
        if (mappings < 0)
            throw new InvalidObjectException("Illegal mappings count: " +
                                               mappings);

        // capacity chosen by number of mappings and desired load (if >= 0.25)
        int capacity = (int) Math.min(
                    mappings * Math.min(1 / loadFactor, 4.0f),
                    // we have limits...
                    HashMap.MAXIMUM_CAPACITY);

        // allocate the bucket array;
        if (mappings > 0) {
            inflateTable(capacity);
        } else {
            threshold = capacity;
        }

        init();  // Give subclass a chance to do its thing.

        // Read the keys and values, and put the mappings in the HashMap
        for (int i = 0; i < mappings; i++) {
            K key = (K) s.readObject();
            V value = (V) s.readObject();
            putForCreate(key, value);
        }
    }

    // These methods are used when serializing HashSets
    int   capacity()     { return table.length; }
    float loadFactor()   { return loadFactor;   }
}
