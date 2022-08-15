/*
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/*
 * Written by Doug Lea with assistance from members of JCP JSR-166
 * Expert Group and released to the public domain, as explained at
 * http://creativecommons.org/publicdomain/zero/1.0/
 */

package java.util.concurrent;
import java.util.concurrent.locks.*;
import java.util.*;
import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;

/**
 * 20210630
 * A. 一个哈希表，支持检索的完全并发性和可调整的更新预期并发性。 此类遵循与 {@link java.util.Hashtable} 相同的功能规范，并包含与 Hashtable 的每个方法对应的方法版本。
 *    但是，即使所有操作都是线程安全的，检索操作也不需要锁定，并且不支持以防止所有访问的方式锁定整个表。 在依赖其线程安全但不依赖其同步细节的程序中，此类与 Hashtable 完全可互操作。
 * B. 检索操作（包括获取）一般不会阻塞，因此可能与更新操作（包括放置和删除）重叠。 检索反映了最近完成的更新操作的结果。 对于诸如 putAll 和 clear 之类的聚合操作，
 *    并发检索可能仅反映某些条目的插入或删除。 类似地，迭代器和枚举返回反映哈希表在迭代器/枚举创建时或创建后的某个时刻的状态的元素。
 *    它们不会抛出 {@link ConcurrentModificationException}。 然而，迭代器被设计为一次只能被一个线程使用。
 * C. 更新操作之间允许的并发性由可选的 concurrencyLevel 构造函数参数（默认为 16）指导，用作内部调整的提示。该表在内部进行了分区，
 *    以尝试允许指定数量的并发更新而不会发生争用。因为散列表中的放置本质上是随机的，所以实际的并发性会有所不同。理想情况下，您应该选择一个值来容纳尽可能多的线程同时修改表。
 *    使用明显高于您需要的值会浪费空间和时间，而明显较低的值会导致线程争用。但一个数量级内的高估和低估通常不会产生太大的影响。
 *    当已知只有一个线程会修改而所有其他线程只会读取时，值1是合适的。此外，调整此哈希表或任何其他类型的哈希表的大小是一个相对较慢的操作，因此，如果可能，
 *    最好在构造函数中提供预期表大小的估计值。
 * D. 此类及其视图和迭代器实现了 {@link Map} 和 {@link Iterator} 接口的所有可选方法。
 * E. 与 {@link Hashtable} 类似但与 {@link HashMap} 不同的是，此类不允许将 null 用作键或值。
 * F. 此类是 Java 集合框架的成员。
 */

/**
 * A.
 * A hash table supporting full concurrency of retrievals and
 * adjustable expected concurrency for updates. This class obeys the
 * same functional specification as {@link java.util.Hashtable}, and
 * includes versions of methods corresponding to each method of
 * <tt>Hashtable</tt>. However, even though all operations are
 * thread-safe, retrieval operations do <em>not</em> entail locking,
 * and there is <em>not</em> any support for locking the entire table
 * in a way that prevents all access.  This class is fully
 * interoperable with <tt>Hashtable</tt> in programs that rely on its
 * thread safety but not on its synchronization details.
 *
 * B.
 * <p> Retrieval operations (including <tt>get</tt>) generally do not
 * block, so may overlap with update operations (including
 * <tt>put</tt> and <tt>remove</tt>). Retrievals reflect the results
 * of the most recently <em>completed</em> update operations holding
 * upon their onset.  For aggregate operations such as <tt>putAll</tt>
 * and <tt>clear</tt>, concurrent retrievals may reflect insertion or
 * removal of only some entries.  Similarly, Iterators and
 * Enumerations return elements reflecting the state of the hash table
 * at some point at or since the creation of the iterator/enumeration.
 * They do <em>not</em> throw {@link ConcurrentModificationException}.
 * However, iterators are designed to be used by only one thread at a time.
 *
 * C.
 * <p> The allowed concurrency among update operations is guided by
 * the optional <tt>concurrencyLevel</tt> constructor argument
 * (default <tt>16</tt>), which is used as a hint for internal sizing.  The
 * table is internally partitioned to try to permit the indicated
 * number of concurrent updates without contention. Because placement
 * in hash tables is essentially random, the actual concurrency will
 * vary.  Ideally, you should choose a value to accommodate as many
 * threads as will ever concurrently modify the table. Using a
 * significantly higher value than you need can waste space and time,
 * and a significantly lower value can lead to thread contention. But
 * overestimates and underestimates within an order of magnitude do
 * not usually have much noticeable impact. A value of one is
 * appropriate when it is known that only one thread will modify and
 * all others will only read. Also, resizing this or any other kind of
 * hash table is a relatively slow operation, so, when possible, it is
 * a good idea to provide estimates of expected table sizes in
 * constructors.
 *
 * D.
 * <p>This class and its views and iterators implement all of the
 * <em>optional</em> methods of the {@link Map} and {@link Iterator}
 * interfaces.
 *
 * E.
 * <p> Like {@link Hashtable} but unlike {@link HashMap}, this class
 * does <em>not</em> allow <tt>null</tt> to be used as a key or value.
 *
 * F.
 * <p>This class is a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @since 1.5
 * @author Doug Lea
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public class ConcurrentHashMap<K, V> extends AbstractMap<K, V> implements ConcurrentMap<K, V>, Serializable {
    private static final long serialVersionUID = 7249069246763182397L;

    /**
     * 20210630
     * A. 基本策略是将表细分到Segment中，每个Segment本身就是一个并发可读的哈希表。为了减少占用空间，只有在第一次需要时才构建除一个段之外的所有段（请参阅 ensureSegment）。
     *    为了在存在延迟构造的情况下保持可见性，对段以及段表的元素的访问必须使用易失性访问，这是通过下面的方法 segmentAt 等中的 Unsafe 完成的。
     *    这些提供了 AtomicReferenceArrays 的功能，但减少了间接级别。 此外，锁定操作中表元素和条目“下一个”字段的易失性写入使用更便宜的“lazySet”形式的写入
     *    （通过 putOrderedObject），因为这些写入总是跟随锁释放，以保持表更新的顺序一致性。
     * B. 历史记录：该类的先前版本严重依赖于“final”字段，它避免了一些易失性读取，但代价是初始占用空间较大。 该设计的一些残余（包括段 0 的强制构造）存在以确保序列化兼容性。
     */
    /*
     * A.
     * The basic strategy is to subdivide the table among Segments,
     * each of which itself is a concurrently readable hash table.  To
     * reduce footprint, all but one segments are constructed only
     * when first needed (see ensureSegment). To maintain visibility
     * in the presence of lazy construction, accesses to segments as
     * well as elements of segment's table must use volatile access,
     * which is done via Unsafe within methods segmentAt etc
     * below. These provide the functionality of AtomicReferenceArrays
     * but reduce the levels of indirection. Additionally,
     * volatile-writes of table elements and entry "next" fields
     * within locked operations use the cheaper "lazySet" forms of
     * writes (via putOrderedObject) because these writes are always
     * followed by lock releases that maintain sequential consistency
     * of table updates.
     *
     * B.
     * Historical note: The previous version of this class relied
     * heavily on "final" fields, which avoided some volatile reads at
     * the expense of a large initial footprint.  Some remnants of
     * that design (including forced construction of segment 0) exist
     * to ensure serialization compatibility.
     */

    /* ---------------- Constants -------------- */

    /**
     * The default initial capacity for this table,
     * used when not otherwise specified in a constructor.
     */
    // 此表的默认初始容量，在构造函数中未指定时使用。
    static final int DEFAULT_INITIAL_CAPACITY = 16;

    /**
     * The default load factor for this table, used when not
     * otherwise specified in a constructor.
     */
    // 此表的默认加载因子，在构造函数中未指定时使用。
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * The default concurrency level for this table, used when not
     * otherwise specified in a constructor.
     */
    // 此表的默认并发级别，在构造函数中未指定时使用。
    static final int DEFAULT_CONCURRENCY_LEVEL = 16;

    /**
     * The maximum capacity, used if a higher value is implicitly
     * specified by either of the constructors with arguments.  MUST
     * be a power of two <= 1<<30 to ensure that entries are indexable
     * using ints.
     */
    // 最大容量，在两个带参数的构造函数隐式指定更高值时使用。 必须是 2 <= 1<<30 的幂，以确保条目可使用整数索引。
    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * The minimum capacity for per-segment tables.  Must be a power
     * of two, at least two to avoid immediate resizing on next use
     * after lazy construction.
     */
    // 每段表的最小容量。必须是2的幂，至少是2以避免在延迟构造后立即调整大小。
    static final int MIN_SEGMENT_TABLE_CAPACITY = 2;

    /**
     * The maximum number of segments to allow; used to bound
     * constructor arguments. Must be power of two less than 1 << 24.
     */
    // 允许的最大段数；用于绑定构造函数参数。必须是小于 1 << 24 的 2 的幂。
    static final int MAX_SEGMENTS = 1 << 16; // slightly conservative

    /**
     * Number of unsynchronized retries in size and containsValue
     * methods before resorting to locking. This is used to avoid
     * unbounded retries if tables undergo continuous modification
     * which would make it impossible to obtain an accurate result.
     */
    // 在求助于锁定之前，未同步重试 size 和 containsValue 方法的次数。 这用于避免在表进行连续修改时无限制的重试，这将导致无法获得准确的结果。
    static final int RETRIES_BEFORE_LOCK = 2;

    /* ---------------- Fields -------------- */

    /**
     * holds values which can't be initialized until after VM is booted.
     */
    // 保存直到 VM 启动后才能初始化的值。
    private static class Holder {

        /**
         * 20210630
         * A. 启用字符串键的替代散列？
         * B. 与其他哈希映射实现不同，我们没有实现一个阈值来调节是否对字符串键使用替代哈希。对所有实例启用或禁用所有实例的替代散列。
         */
        /**
         * A.
        * Enable alternative hashing of String keys?
        *
        * B.
        * <p>Unlike the other hash map implementations we do not implement a
        * threshold for regulating whether alternative hashing is used for
        * String keys. Alternative hashing is either enabled for all instances
        * or disabled for all instances.
        */
        static final boolean ALTERNATIVE_HASHING;

        static {
            // Use the "threshold" system property even though our threshold
            // behaviour is "ON" or "OFF".
            // 即使我们的阈值行为是“ON”或“OFF”，也要使用“threshold”系统属性。
            String altThreshold = java.security.AccessController.doPrivileged(
                new sun.security.action.GetPropertyAction(
                    "jdk.map.althashing.threshold"));

            int threshold;
            try {
                threshold = (null != altThreshold)
                        ? Integer.parseInt(altThreshold)
                        : Integer.MAX_VALUE;

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
            ALTERNATIVE_HASHING = threshold <= MAXIMUM_CAPACITY;
        }
    }

    /**
     * A randomizing value associated with this instance that is applied to
     * hash code of keys to make hash collisions harder to find.
     */
    // 哈希种子可以让哈希算法更复杂一点，从而减少哈希碰撞的概率，如果为0代表哈希种子不生效
    private transient final int hashSeed = randomHashSeed(this);

    private static int randomHashSeed(ConcurrentHashMap instance) {
        if (sun.misc.VM.isBooted() && Holder.ALTERNATIVE_HASHING) {
            return sun.misc.Hashing.randomHashSeed(instance);
        }

        return 0;
    }

    /**
     * Mask value for indexing into segments. The upper bits of a
     * key's hash code are used to choose the segment.
     */
    // 用于索引段的掩码值。 密钥散列码的高位用于选择段。
    // ssize-1, 得到segment[]长度的掩码, 在(hash(key) >>> segmentShift) & segmentMask中, 用来判断key到底落在哪个segment上
    final int segmentMask;

    /**
     * Shift value for indexing within segments.
     */
    // 段内索引的移位值。
    // 32-幂次sshift, 得到剩余高位的位数, 在(hash(key) >>> segmentShift) & segmentMask中, 用来判断key到底落在哪个segment上
    final int segmentShift;

    /**
     * The segments, each of which is a specialized hash table.
     */
    // 段，每个段都是一个专门的哈希表。
    // Segment[], 代表ConcurrentHashMap的直接数据结构, 里面每个Segment含有table, 可以看作是一个小的HashMap
    final Segment<K,V>[] segments;

    transient Set<K> keySet;
    transient Set<Map.Entry<K,V>> entrySet;
    transient Collection<V> values;

    /**
     * ConcurrentHashMap list entry. Note that this is never exported
     * out as a user-visible Map.Entry.
     */
    // ConcurrentHashMap列表条目。请注意，这永远不会作为用户可见的 Map.Entry 导出。
    static final class HashEntry<K,V> {
        final int hash;
        final K key;
        volatile V value;
        volatile HashEntry<K,V> next;

        HashEntry(int hash, K key, V value, HashEntry<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        /**
         * Sets next field with volatile write semantics.  (See above
         * about use of putOrderedObject.)
         */
        // 使用易失性写入语义设置下一个字段。 （请参阅上文关于 putOrderedObject 的使用。）
        final void setNext(HashEntry<K,V> n) {
            UNSAFE.putOrderedObject(this, nextOffset, n);
        }

        // Unsafe mechanics
        static final sun.misc.Unsafe UNSAFE;
        static final long nextOffset;
        static {
            try {
                UNSAFE = sun.misc.Unsafe.getUnsafe();
                Class k = HashEntry.class;
                nextOffset = UNSAFE.objectFieldOffset
                    (k.getDeclaredField("next"));
            } catch (Exception e) {
                throw new Error(e);
            }
        }
    }

    /**
     * 20210703
     * 使用volatile读取语义获取给定表的第 i 个元素（如果非空）。 注意：这是手动集成到一些性能敏感的方法中以减少调用开销。
     */
    /**
     * Gets the ith element of given table (if nonnull) with volatile
     * read semantics. Note: This is manually integrated into a few
     * performance-sensitive methods to reduce call overhead.
     */
    // 通过Unsafe方法获取主内存中tab表i位置的元素
    @SuppressWarnings("unchecked")
    static final <K,V> HashEntry<K,V> entryAt(HashEntry<K,V>[] tab, int i) {
        return (tab == null) ? null : (HashEntry<K,V>) UNSAFE.getObjectVolatile(tab, ((long)i << TSHIFT) + TBASE);
    }

    /**
     * 20210703
     * 设置给定表的第 i 个元素，具有可变写入语义。 （请参阅上文关于 putOrderedObject 的使用。）
     */
    /**
     * Sets the ith element of given table, with volatile write
     * semantics. (See above about use of putOrderedObject.)
     */
    // 通过Unsafe方法更新主内存中tab表i位置的元素为e
    static final <K,V> void setEntryAt(HashEntry<K,V>[] tab, int i, HashEntry<K,V> e) {
        UNSAFE.putOrderedObject(tab, ((long)i << TSHIFT) + TBASE, e);
    }

    /**
     * 20210630
     * 将补充散列函数应用于给定的 hashCode，以防止质量差的散列函数。这很关键，因为 ConcurrentHashMap 使用长度为2的幂的哈希表，
     * 否则会遇到低位或高位没有不同的 hashCode 的冲突。
     */
    /**
     * Applies a supplemental hash function to a given hashCode, which
     * defends against poor quality hash functions.  This is critical
     * because ConcurrentHashMap uses power-of-two length hash tables,
     * that otherwise encounter collisions for hashCodes that do not
     * differ in lower or upper bits.
     */
    // HashCode扰动函数, key为String类型则返回key的32位哈希码即可, 否则哈希种子异或k的散列码, 再把结果高位的特征和低位的特征组合起来，降低哈希冲突的概率，也就是说，尽量做到任何一位的变化都能对最终得到的结果产生影响
    private int hash(Object k) {
        // 哈希种子可以让哈希算法更复杂一点，从而减少哈希碰撞的概率，如果为0代表哈希种子不生效
        int h = hashSeed;

        // 如果k为String类型, 则返回k的32位哈希值
        if ((0 != h) && (k instanceof String)) {
            return sun.misc.Hashing.stringHash32((String) k);
        }

        // 如果k不为String类型, 则哈希种子异或k的散列码, 再把结果高位的特征和低位的特征组合起来，降低哈希冲突的概率，也就是说，尽量做到任何一位的变化都能对最终得到的结果产生影响
        // 如果h为默认0, 则h异或k的散列表码, 还是会等于k的散列码
        h ^= k.hashCode();

        // 使用单字 Wang/Jenkins 散列的变体，展开位以正则化段和索引位置。
        // Spread bits to regularize both segment and index locations,
        // using variant of single-word Wang/Jenkins hash.
        // 产生的Hash值既会用来定位Segment索引, 又会定位Segment#table中的索引 => 把高位的特征和低位的特征组合起来，降低哈希冲突的概率，也就是说，尽量做到任何一位的变化都能对最终得到的结果产生影响
        h += (h <<  15) ^ 0xffffcd7d;
        h ^= (h >>> 10);
        h += (h <<   3);
        h ^= (h >>>  6);
        h += (h <<   2) + (h << 14);
        return h ^ (h >>> 16);
    }

    /**
     * 20210703
     * 段是哈希表的特殊版本。这个子类是 ReentrantLock 的子类，只是为了简化一些锁定并避免单独构造。
     */
    /**
     * Segments are specialized versions of hash tables.  This
     * subclasses from ReentrantLock opportunistically, just to
     * simplify some locking and avoid separate construction.
     */
    // Segment[]中的每段Segment, 类似于一个HashMap, 但本身又继承了ReentrantLock, 所以每段Segment还是一把锁, 更新Segment需要获取该锁, 从而保证线程安全
    static final class Segment<K,V> extends ReentrantLock implements Serializable {

        /**
         * 20210630
         * A. 段维护一个始终保持一致状态的条目列表表，因此可以在不锁定的情况下读取（通过段和表的易失性读取）。这需要在调整表大小期间在必要时复制节点，
         *    因此仍然使用旧版本表的读者可以遍历旧列表。
         * B. 此类仅定义需要锁定的可变方法。除非另有说明，此类的方法执行ConcurrentHashMap方法的每个段版本。（其他方法直接集成到ConcurrentHashMap方法中。）
         *    这些可变方法使用一种通过方法scanAndLock和scanAndLockForPut对争用进行受控旋转的形式。这些穿插 tryLocks 与遍历以定位节点。
         *    主要的好处是在获取锁的同时吸收缓存未命中（这对于哈希表很常见），以便在获取后更快地遍历。 我们实际上并不使用找到的节点，
         *    因为无论如何它们必须在锁定下重新获取以确保更新的顺序一致性（并且在任何情况下都可能无法检测到陈旧），但它们通常会更快地重新定位。 此外，如果没有找到节点，
         *    scanAndLockForPut 会推测性地创建一个新节点以用于 put 。
         */
        /*
         * A.
         * Segments maintain a table of entry lists that are always
         * kept in a consistent state, so can be read (via volatile
         * reads of segments and tables) without locking.  This
         * requires replicating nodes when necessary during table
         * resizing, so the old lists can be traversed by readers
         * still using old version of table.
         *
         * B.
         * This class defines only mutative methods requiring locking.
         * Except as noted, the methods of this class perform the
         * per-segment versions of ConcurrentHashMap methods.  (Other
         * methods are integrated directly into ConcurrentHashMap
         * methods.) These mutative methods use a form of controlled
         * spinning on contention via methods scanAndLock and
         * scanAndLockForPut. These intersperse tryLocks with
         * traversals to locate nodes.  The main benefit is to absorb
         * cache misses (which are very common for hash tables) while
         * obtaining locks so that traversal is faster once
         * acquired. We do not actually use the found nodes since they
         * must be re-acquired under lock anyway to ensure sequential
         * consistency of updates (and in any case may be undetectably
         * stale), but they will normally be much faster to re-locate.
         * Also, scanAndLockForPut speculatively creates a fresh node
         * to use in put if no node is found.
         */

        private static final long serialVersionUID = 2249069246763182397L;

        /**
         * 20210703
         * 在可能阻塞获取以准备锁定段操作之前，预扫描中 tryLock 的最大次数。 在多处理器上，使用有限次数的重试来维护在定位节点时获取的缓存。
         */
        /**
         * The maximum number of times to tryLock in a prescan before
         * possibly blocking on acquire in preparation for a locked
         * segment operation. On multiprocessors, using a bounded
         * number of retries maintains cache acquired while locating
         * nodes.
         */
        // 预扫描中tryLock的最大次数
        static final int MAX_SCAN_RETRIES = Runtime.getRuntime().availableProcessors() > 1 ? 64 : 1;

        /**
         * 20210703
         * 每段表。 元素通过 entryAt/setEntryAt 访问，提供易变的语义。
         */
        /**
         * The per-segment table. Elements are accessed via
         * entryAt/setEntryAt providing volatile semantics.
         */
        // Segment[]中的每段Segment中的散列表, 相当于HashMap中的table
        transient volatile HashEntry<K,V>[] table;

        /**
         * 20210703
         * 元素的数量。仅在锁内或在保持可见性的其他volatile读取中访问。
         */
        /**
         * The number of elements. Accessed only either within locks
         * or among other volatile reads that maintain visibility.
         */
        // Segment[]中的每段Segment中的散列表元素个数, 相当于HashMap中的table的实际大小
        transient int count;

        /**
         * 20210703
         * 此段中的可变操作总数。尽管这可能会溢出32位，但它为CHM isEmpty()和size()方法中的稳定性检查提供了足够的准确性。仅在锁内或在保持可见性的其他volatile读取中访问。
         */
        /**
         * The total number of mutative operations in this segment.
         * Even though this may overflows 32 bits, it provides
         * sufficient accuracy for stability checks in CHM isEmpty()
         * and size() methods.  Accessed only either within locks or
         * among other volatile reads that maintain visibility.
         */
        // Segment[]中的每段Segment中的散列表修改模数, 相当于HashMap中的table的修改模数
        transient int modCount;

        /**
         * 20210703
         * 当表的大小超过此阈值时，该表将被重新散列。（此字段的值始终为 (int)(capacity * loadFactor））
         */
        /**
         * The table is rehashed when its size exceeds this threshold.
         * (The value of this field is always <tt>(int)(capacity *
         * loadFactor)</tt>.)
         */
        // Segment[]中的每段Segment中的散列表阈值, 相当于HashMap中的table的阈值
        transient int threshold;

        /**
         * 20210703
         * 哈希表的负载因子。即使所有段的此值都相同，它也会被复制以避免需要到外部对象的链接。
         */
        /**
         * The load factor for the hash table.  Even though this value
         * is same for all segments, it is replicated to avoid needing
         * links to outer object.
         * @serial
         */
        // Segment[]中的每段Segment中的散列表负载因子, 相当于HashMap中的table的负载因子
        final float loadFactor;

        // 指定负载因子、阈值、散列表来构造段
        Segment(float lf, int threshold, HashEntry<K,V>[] tab) {
            this.loadFactor = lf;
            this.threshold = threshold;
            this.table = tab;
        }

        // 获取当前segment锁后, 往其散列表添加key-value条目, onlyIfAbsent为true代表只允许key对应结点不存在时才添加, 为false代表key对应结点已存在时可以发生值替换; 如果散列表实际大小超过阈值, 则还会发生扩容
        final V put(K key, int hash, V value, boolean onlyIfAbsent) {
            // 快速失败方式获取当前segment锁, 如果获取到, 则node为null, 代表node结点没有创建; 否则自旋获取当前segment锁, 自旋期间会尝试创建node结点, 如果自旋次数超过最大值还会阻塞获取锁, 直到获取到锁才返回
            HashEntry<K,V> node = tryLock() ? null : scanAndLockForPut(key, hash, value);
            V oldValue;

            try {
                // 到这里, 当前线程获取到了当前segment锁, 提前创建的结点node, segment散列表tab, hash值表索引为index
                HashEntry<K,V>[] tab = table;
                int index = (tab.length - 1) & hash;

                // 通过Unsafe方法获取主内存中tab表index位置的元素first, 此时first为最新的桶头结点, 且当前处于锁定状态中, 肯定不会被其他线程更新了
                HashEntry<K,V> first = entryAt(tab, index);

                // 遍历first桶链表, 当前遍历元素e
                for (HashEntry<K,V> e = first;;) {
                    // 如果e不为null, 说明需要判断e链表中是否已存在key对应的元素
                    if (e != null) {
                        K k;

                        // 如果key等于e键或者e的hash值等于hash且key equals e键, 说明找到了key对应的结点e
                        if ((k = e.key) == key || (e.hash == hash && key.equals(k))) {
                            oldValue = e.value;

                            // 此时如果需要果只允许在不存在时才添加, 则什么也不做, 否则替换旧值, 结束遍历
                            if (!onlyIfAbsent) {
                                e.value = value;
                                ++modCount;
                            }
                            break;
                        }

                        // 如果没找到key对应结点e, 则继续遍历
                        e = e.next;
                    }

                    // 如果e为null, 说明first链表中不存在key对应的元素, 则头插法插入一个新的entry
                    else {
                        // 如果node结点不为null, 说明node结点已经提前创建了, 则头插法插入到桶头结点即可
                        if (node != null)
                            node.setNext(first);
                        // 如果node结点为null, 说明node结点没有提前创建, 则在桶头位置创建node结点
                        else
                            node = new HashEntry<K,V>(hash, key, value, first);

                        // node结点添加到桶头结点后, 如果当前segment的实际大小大于阈值, 且容量小于最大容量, 则扩容当前segment的散列表table
                        int c = count + 1;
                        if (c > threshold && tab.length < MAXIMUM_CAPACITY)
                            // 由于这里已经对当前segment进行上锁了, 也就是当前table不会被其他线程更改, 因此扩容也是线程安全的
                            // 扩容当前segment的散列表, 重新计算每个链表的hash值, 并分成高低链表转移到新表中, 最后把要插入的node结点也插入到新表中, 更新当前segment的散列表为新表
                            rehash(node);

                        // 如果当前segment的散列表不需要扩容, 则通过Unsafe方法更新主内存中tab表i位置的元素为e
                        else
                            setEntryAt(tab, index, node);

                        // 最后更新修改模数、实际大小, 设置旧值为null, 代表插入成功, 结束遍历
                        ++modCount;
                        count = c;
                        oldValue = null;
                        break;
                    }
                }
            } finally {
                // 最后释放segment锁
                unlock();
            }

            // 返回null代表添加成功, 返回旧值代表key对应结点已存在, 当onlyIfAbsent为false则还发生了值替换
            return oldValue;
        }

        /**
         * 20210703
         * 将表的大小加倍并重新打包条目，还将给定节点添加到新表
         */
        /**
         * Doubles size of table and repacks entries, also adding the
         * given node to new table
         */
        // 扩容当前segment的散列表, 重新计算每个链表结点hash值对应新表的索引, 并分成高低链表转移到新表中, 最后把要插入的node结点也插入到新表中, 更新当前segment的散列表为新表
        @SuppressWarnings("unchecked")
        private void rehash(HashEntry<K,V> node) {

            /**
             * 20210630
             * 将每个列表中的节点重新分类为新表。因为我们使用的是二次幂扩展，所以每个bin中的元素必须保持相同的索引，或者以二次幂的偏移量移动。
             * 我们通过捕获可以重用旧节点的情况来消除不必要的节点创建，因为它们的下一个字段不会改变。 据统计，在默认阈值下，当表翻倍时，只有大约六分之一需要克隆。
             * 一旦它们不再被可能处于并发遍历表中的任何读取器线程引用，它们替换的节点将是可垃圾回收的。入口访问使用普通数组索引，因为它们之后是易失性表写入。
             */
            /*
             * Reclassify nodes in each list to new table.  Because we
             * are using power-of-two expansion, the elements from
             * each bin must either stay at same index, or move with a
             * power of two offset. We eliminate unnecessary node
             * creation by catching cases where old nodes can be
             * reused because their next fields won't change.
             * Statistically, at the default threshold, only about
             * one-sixth of them need cloning when a table
             * doubles. The nodes they replace will be garbage
             * collectable as soon as they are no longer referenced by
             * any reader thread that may be in the midst of
             * concurrently traversing table. Entry accesses use plain
             * array indexing because they are followed by volatile
             * table write.
             */
            // 旧表oldTable, 旧表容量oldCapacity, 新表容量newCapacity, 新表阈值threshold, 新表掩码sizeMask
            HashEntry<K,V>[] oldTable = table;
            int oldCapacity = oldTable.length;
            int newCapacity = oldCapacity << 1;
            threshold = (int)(newCapacity * loadFactor);
            HashEntry<K,V>[] newTable =
                (HashEntry<K,V>[]) new HashEntry[newCapacity];
            int sizeMask = newCapacity - 1;

            // 从头开始遍历旧表oldTable, 当前遍历结点e
            for (int i = 0; i < oldCapacity ; i++) {
                HashEntry<K,V> e = oldTable[i];

                // 如果e结点不为null, 说明e桶链表存在结点, 则遍历e链表, 后继next, e桶头结点在新表中的索引idx
                if (e != null) {
                    HashEntry<K,V> next = e.next;
                    int idx = e.hash & sizeMask;

                    // 如果后继为null, 说明e链表只有一个桶头结点e, 则移动e到新表即可
                    if (next == null)   //  Single node on list 列表中的单个节点
                        newTable[idx] = e;
                    // 如果后继不为null, 说明需要转移链表到新表中
                    else { // Reuse consecutive sequence at same slot 在同一时隙重用连续序列
                        HashEntry<K,V> lastRun = e;
                        int lastIdx = idx;

                        // 遍历e链表, 找出最后一段头开始的结点lastRun(一般来说只有两段, 因为扩容成两倍只差1位), 对应索引lastIdx
                        for (HashEntry<K,V> last = next; last != null; last = last.next) {
                            int k = last.hash & sizeMask;
                            if (k != lastIdx) {
                                lastIdx = k;
                                lastRun = last;
                            }
                        }

                        // 找到lastRun后, 转移lastRun链表到新表的lastIdx位置, 完成高位链表转移
                        newTable[lastIdx] = lastRun;


                        // Clone remaining nodes 克隆剩余节点
                        // 如果低位还有结点, 则从头开始遍历到lastRun之前, 头插法插入每个遍历到的结点到新表的k位置
                        for (HashEntry<K,V> p = e; p != lastRun; p = p.next) {
                            V v = p.value;
                            int h = p.hash;
                            int k = h & sizeMask;
                            HashEntry<K,V> n = newTable[k];
                            newTable[k] = new HashEntry<K,V>(h, p.key, v, n);
                        }
                    }
                }
            }

            // 重新计算要插入node结点在新表中的索引, 并更新node.next为新表索引中的桶头结点, 头插法把node结点插入新表中
            int nodeIndex = node.hash & sizeMask; // add the new node
            node.setNext(newTable[nodeIndex]);
            newTable[nodeIndex] = node;

            // 更新当前segment的散列表为新表
            table = newTable;
        }

        /**
         * 20210703
         * 在尝试获取锁时扫描包含给定密钥的节点，如果未找到则创建并返回一个。 返回时，保证持有锁。 与大多数方法不同，对方法 equals 的调用不会被屏蔽：
         * 由于遍历速度无关紧要，我们不妨帮助预热相关的代码和访问。
         */
        /**
         * Scans for a node containing given key while trying to
         * acquire lock, creating and returning one if not found. Upon
         * return, guarantees that lock is held. UNlike in most
         * methods, calls to method equals are not screened: Since
         * traversal speed doesn't matter, we might as well help warm
         * up the associated code and accesses as well.
         *
         * // 如果未找到键，则为新节点，否则为空
         * @return a new node if key not found, else null
         */
        // 自旋获取当前segment锁, 自旋期间会尝试创建node结点, 如果自旋次数超过最大值还会阻塞获取锁, 直到获取到锁才返回
        private HashEntry<K,V> scanAndLockForPut(K key, int hash, V value) {
            // 获取指定段seg中散列表的给定hash值的条目first, first备份条目e, 要返回的条目node, 新桶头结点f, 重试次数retries(初始时为-1)
            HashEntry<K,V> first = entryForHash(this, hash);
            HashEntry<K,V> e = first;
            HashEntry<K,V> node = null;
            int retries = -1; // negative while locating node 定位节点时为负

            // 快速失败自旋获取当前segment锁, 如果获取不到, 则期间会尝试创建node结点
            while (!tryLock()) {
                HashEntry<K,V> f; // to recheck first below 在下面首先重新检查

                // 如果retries < 0, 说明node结点还没找到或者创建, 则遍历e链表
                if (retries < 0) {
                    // 如果e为null, 说明要么桶头结点first为null, 要么遍历到了e链尾还没找到node结点, 则创建node结点
                    if (e == null) {
                        if (node == null) // speculatively create node 推测创建节点
                            node = new HashEntry<K,V>(hash, key, value, null);

                        // 更新retries为0, 代表node结点已创建
                        retries = 0;
                    }
                    // 如果e不为null, 说明正在遍历链表, 如果key euqals e键, 说明e就是要找的结点, 则更新retries为0, 代表node结点已找到, 此时返回node结点为不为null也没关系, 因为外层逻辑不会走node设置到桶头结点的逻辑
                    else if (key.equals(e.key))
                        retries = 0;
                    // 如果e不为null, 且key不equals e键, 则继续遍历e链表
                    else
                        e = e.next;
                }

                // 如果retries >= 0, 说明node结点已找到或者已创建, 则继续自旋获取锁, 直到达到最大重试次数
                else if (++retries > MAX_SCAN_RETRIES) {
                    // 否则, 阻塞获取锁, 直到获取到锁才返回
                    lock();
                    break;
                }

                // 如果自旋次数达到了最大值, 且重试次数为奇数次时, 则重新获取指定段seg中散列表的给定hash值的条目f, 如果f不等于first, 说明桶头结点被更改了, 则重新更新first和e为新的桶头结点, 且重新自旋
                else if ((retries & 1) == 0 && (f = entryForHash(this, hash)) != first) {
                    e = first = f; // re-traverse if entry changed
                    retries = -1;
                }
            }

            // 自旋结束, 代表当前线程获取到了当前segment锁
            // node结点为null代表在e链表中找到了key对应的结点;
            // node结点不为null, 可能代表无论是新旧e链表中, 肯定不存在key对应的结点, 此时node结点返回后肯定会被当做桶头结点插入
            // node结点不为null, 可能还代表原e链表中找不到且创建了node结点, 但原e链表被其他线程更新了, 且新e链表存在key对应的结点, 此时返回node结点为不为null也没关系, 因为外层逻辑不会走node设置到桶头结点的逻辑
            return node;
        }

        /**
         * 20210703
         * 在尝试获取移除或替换操作的锁时扫描包含给定键的节点。返回时，保证持有锁。 请注意，即使找不到密钥，我们也必须锁定，以确保更新的顺序一致性。
         */
        /**
         * Scans for a node containing the given key while trying to
         * acquire lock for a remove or replace operation. Upon
         * return, guarantees that lock is held.  Note that we must
         * lock even if the key is not found, to ensure sequential
         * consistency of updates.
         */
        // 自旋获取当前segment锁, 自旋期间不会尝试创建node结点但会遍历e链表, 如果自旋次数超过最大值还会阻塞获取锁, 直到获取到锁才返回
        private void scanAndLock(Object key, int hash) {
            // similar to but simpler than scanAndLockForPut 类似于但比 scanAndLockForPut 更简单
            // 获取指定段seg中散列表的给定hash值的条目first, first备份条目e, 新桶头结点f, 重试次数retries(初始时为-1)
            HashEntry<K,V> first = entryForHash(this, hash);
            HashEntry<K,V> e = first;
            int retries = -1;

            // 快速失败自旋获取当前segment锁, 对比scanAndLockForPut, 如果获取不到, 则期间并不会尝试创建node结点
            while (!tryLock()) {
                HashEntry<K,V> f;

                // 如果retries < 0, 说明node结点还没已找到或者e链表还没遍历完, 则遍历e链表
                if (retries < 0) {
                    // 如果e为null, 说明遍历到了e链尾还没找到node结点, 或者如果找到了key对应的node, 则停止遍历
                    if (e == null || key.equals(e.key))
                        // 更新retries为0, 代表node结点已找到或者已经遍历完了
                        retries = 0;
                     // 如果e不为null, 且key不equals e键, 则继续遍历e链表
                    else
                        e = e.next;
                }

                // 如果retries >= 0, 说明node结点已找到或者已经遍历完了, 则继续自旋获取锁, 直到达到最大重试次数
                else if (++retries > MAX_SCAN_RETRIES) {
                    // 否则, 阻塞获取锁, 直到获取到锁才返回
                    lock();
                    break;
                }

                // 如果自旋次数达到了最大值, 且重试次数为奇数次时, 则重新获取指定段seg中散列表的给定hash值的条目f, 如果f不等于first, 说明桶头结点被更改了, 则重新更新first和e为新的桶头结点, 且重新自旋
                else if ((retries & 1) == 0 && (f = entryForHash(this, hash)) != first) {
                    e = first = f;
                    retries = -1;
                }
            }
        }

        /**
         * 20210703
         * 删除; 仅当值为 null 时才匹配键，否则匹配两者。
         */
        /**
         * Remove; match on key only if value null, else match both.
         */
        // 获取当前segment锁后, 删除散列表中指定key对应的结点, 如果指定了value则key、value都匹配才能删除结点, 删除成功则返回旧值, 否则返回null
        final V remove(Object key, int hash, Object value) {
            // 快速失败方式获取当前segment锁, 如果获取失败, 则自旋获取当前segment锁, 自旋期间不会尝试创建node结点但会遍历e链表, 如果自旋次数超过最大值还会阻塞获取锁, 直到获取到锁才返回
            if (!tryLock())
                scanAndLock(key, hash);
            V oldValue = null;

            try {
                // 到这里, 当前线程获取到了当前segment锁, segment散列表tab, hash值表索引为index
                HashEntry<K,V>[] tab = table;
                int index = (tab.length - 1) & hash;

                // 通过Unsafe方法获取主内存中tab表i位置的元素e, 前驱pred
                HashEntry<K,V> e = entryAt(tab, index);
                HashEntry<K,V> pred = null;

                // 如果e不为null, 说明需要遍历e链表, e键k, e后继next, e值v
                while (e != null) {
                    K k;
                    HashEntry<K,V> next = e.next;

                    // 如果e键等于key, 或者e的hash值等于hash且e键equals key, 说明e就是要找的结点
                    if ((k = e.key) == key || (e.hash == hash && key.equals(k))) {
                        V v = e.value;

                        // 如果没有指定value, 说明e就是要找的结点, 需要删除
                        // 如果指定了value, 则还需要匹配value是否相等, 如果e值等于value, 或者e值equals value, 说明e就是要找的结点, 需要删除
                        if (value == null || value == v || value.equals(v)) {
                            // 如果前驱为null, 说明e为桶头结点, 则更新后继作为桶头结点, 脱钩e结点
                            if (pred == null)
                                setEntryAt(tab, index, next);
                            // 如果前驱不为null, 说明e不为桶头结点, 则链接前驱与后继, 脱钩e结点
                            else
                                pred.setNext(next);

                            // 删除成功后, 则更新修改模数、更新实际大小、设置旧值为e值v, 结束遍历
                            ++modCount;
                            --count;
                            oldValue = v;
                        }
                        break;
                    }
                    pred = e;
                    e = next;
                }
            } finally {
                // 最后释放segment锁
                unlock();
            }

            // 删除成功则返回旧值, 否则返回null
            return oldValue;
        }

        final boolean replace(K key, int hash, V oldValue, V newValue) {
            if (!tryLock())
                scanAndLock(key, hash);
            boolean replaced = false;
            try {
                HashEntry<K,V> e;
                for (e = entryForHash(this, hash); e != null; e = e.next) {
                    K k;
                    if ((k = e.key) == key ||
                        (e.hash == hash && key.equals(k))) {
                        if (oldValue.equals(e.value)) {
                            e.value = newValue;
                            ++modCount;
                            replaced = true;
                        }
                        break;
                    }
                }
            } finally {
                unlock();
            }
            return replaced;
        }

        final V replace(K key, int hash, V value) {
            if (!tryLock())
                scanAndLock(key, hash);
            V oldValue = null;
            try {
                HashEntry<K,V> e;
                for (e = entryForHash(this, hash); e != null; e = e.next) {
                    K k;
                    if ((k = e.key) == key ||
                        (e.hash == hash && key.equals(k))) {
                        oldValue = e.value;
                        e.value = value;
                        ++modCount;
                        break;
                    }
                }
            } finally {
                unlock();
            }
            return oldValue;
        }

        final void clear() {
            lock();
            try {
                HashEntry<K,V>[] tab = table;
                for (int i = 0; i < tab.length ; i++)
                    setEntryAt(tab, i, null);
                ++modCount;
                count = 0;
            } finally {
                unlock();
            }
        }
    }

    // Accessing segments

    /**
     * 20210703
     * 通过 Unsafe 获取具有可变元素访问语义的给定段数组（如果非空）的第 j 个元素。 （空检查只能在反序列化期间无害地触发。）
     * 注意：因为段数组的每个元素只设置一次（使用完全有序的写入），一些性能敏感的方法仅依赖此方法作为对空读取的重新检查。
     */
    /**
     * Gets the jth element of given segment array (if nonnull) with
     * volatile element access semantics via Unsafe. (The null check
     * can trigger harmlessly only during deserialization.) Note:
     * because each element of segments array is set only once (using
     * fully ordered writes), some performance-sensitive methods rely
     * on this method only as a recheck upon null reads.
     */
    // 获取主内存的Segment[j]对象
    @SuppressWarnings("unchecked")
    static final <K,V> Segment<K,V> segmentAt(Segment<K,V>[] ss, int j) {
        // j为在Segment[]中的索引, 而SBASE为Segment[]的起始内存地址, ss为数组中每个对象的内存块大小, SSHIFT为sc高位1后的位数, 当ss为2的幂次时, j<<SSHIFT相当于j*ss, 因此u相当于Segment[j]对象的偏移
        long u = (j << SSHIFT) + SBASE;

        // 获取主内存的Segment[j]
        return ss == null ? null : (Segment<K,V>) UNSAFE.getObjectVolatile(ss, u);
    }

    /**
     * 20210703
     * 返回给定索引的段，创建它并记录在段表中（通过 CAS）（如果不存在）。
     */
    /**
     * Returns the segment for the given index, creating it and
     * recording in segment table (via CAS) if not already present.
     *
     * @param k the index
     * @return the segment
     */
    // 返回k段, 如果不存在, 则创建它并通过CAS记录在segment[k]中
    @SuppressWarnings("unchecked")
    private Segment<K,V> ensureSegment(int k) {
        // Segment数组segments, k段偏移u, k段seg
        final Segment<K,V>[] ss = this.segments;
        long u = (k << SSHIFT) + SBASE; // raw offset
        Segment<K,V> seg;

        // 如果k段seg为null, 说明k段Segment还没初始化, 则使用段0作为原型创建Segment
        if ((seg = (Segment<K,V>)UNSAFE.getObjectVolatile(ss, u)) == null) {
            // 第0段proto, proto散列表容量cap, proto负载因子lf, cap*lf计算出阈值threshold
            Segment<K,V> proto = ss[0]; // use segment 0 as prototype 使用段 0 作为原型
            int cap = proto.table.length;
            float lf = proto.loadFactor;
            int threshold = (int)(cap * lf);

            // 使用第0段proto作为原型, 构造出同样容量cap的散列表tab
            HashEntry<K,V>[] tab = (HashEntry<K,V>[])new HashEntry[cap];

            // 再次检查k段是否为null, 如果为null, 说明还没被其他线程赋值, 则构建Segment对象, 并CAS更新到k段位置
            if ((seg = (Segment<K,V>)UNSAFE.getObjectVolatile(ss, u)) == null) { // recheck
                Segment<K,V> s = new Segment<K,V>(lf, threshold, tab);
                while ((seg = (Segment<K,V>)UNSAFE.getObjectVolatile(ss, u)) == null) {
                    if (UNSAFE.compareAndSwapObject(ss, u, null, seg = s))
                        break;
                }
            }
        }

        // 最后无论当前线程实际有没有创建k段Segment, 都返回k段Segment
        return seg;
    }

    // Hash-based segment and entry accesses

    /**
     * Get the segment for the given hash
     */
    // 获取给定hash值的段
    @SuppressWarnings("unchecked")
    private Segment<K,V> segmentForHash(int h) {

        // h >>> segmentShift, 相当于取 hash的高ssize位数值 % (ssize-1), 得到hash在segment中的索引,
        // 而SBASE为Segment[]的起始内存地址, ss为数组中每个对象的内存块大小, SSHIFT为sc高位1后的位数, 当ss为2的幂次时, j<<SSHIFT相当于j*ss,
        // (((h >>> segmentShift) & segmentMask) << SSHIFT) + SBASE, 直接获取Segment[]中hash值对应的索引j个对象
        long u = (((h >>> segmentShift) & segmentMask) << SSHIFT) + SBASE;
        return (Segment<K,V>) UNSAFE.getObjectVolatile(segments, u);
    }

    /**
     * Gets the table entry for the given segment and hash
     */
    // 获取指定段seg中散列表的给定hash值的条目
    @SuppressWarnings("unchecked")
    static final <K,V> HashEntry<K,V> entryForHash(Segment<K,V> seg, int h) {
        HashEntry<K,V>[] tab;
        return (seg == null || (tab = seg.table) == null) ? null :
                // (tab.length - 1) & h, 计算出h所在散列表的索引, 再((tab.length - 1) & h <<< TSHIFT) + TBASE获取当前索引在table数组中的对象
            (HashEntry<K,V>) UNSAFE.getObjectVolatile(tab, ((long)(((tab.length - 1) & h)) << TSHIFT) + TBASE);
    }

    /* ---------------- Public operations -------------- */

    /**
     * Creates a new, empty map with the specified initial
     * capacity, load factor and concurrency level.
     *
     * // 初始容量。该实现执行内部大小调整以容纳这么多元素。
     * @param initialCapacity the initial capacity. The implementation
     * performs internal sizing to accommodate this many elements.
     *
     * // 负载因子阈值，用于控制调整大小。 当每个 bin 的平均元素数超过此阈值时，可以执行调整大小。
     * @param loadFactor  the load factor threshold, used to control resizing.
     * Resizing may be performed when the average number of elements per
     * bin exceeds this threshold.
     *
     * // 估计的并发更新线程数。该实现执行内部大小调整以尝试容纳这么多线程。
     * @param concurrencyLevel the estimated number of concurrently
     * updating threads. The implementation performs internal sizing
     * to try to accommodate this many threads.
     *
     * // 如果初始容量为负或负载因子或 concurrencyLevel 为非正。
     * @throws IllegalArgumentException if the initial capacity is
     * negative or the load factor or concurrencyLevel are
     * nonpositive.
     */
    // 使用指定的初始容量、负载因子和并发数量创建一个新的空映射, segment[]长度sszie为2的幂次(concurrencyLevel), 每个segment里table的长度为max(2的幂次(initialCapacity/sszie), 2)
    @SuppressWarnings("unchecked")
    public ConcurrentHashMap(int initialCapacity, float loadFactor, int concurrencyLevel) {
        if (!(loadFactor > 0) || initialCapacity < 0 || concurrencyLevel <= 0)
            throw new IllegalArgumentException();
        
        // 如果指定的concurrencyLevel 大于 允许的最大段数1<< 16, 则设置concurrencyLevel为允许的最大段数
        if (concurrencyLevel > MAX_SEGMENTS)
            concurrencyLevel = MAX_SEGMENTS;

        // 查找最佳匹配参数的二的幂
        // Find power-of-two sizes best matching arguments
        int sshift = 0;// ssize的幂次sshift, 代表segment[]长度在低位占据几个1
        int ssize = 1;// ssize=2的幂次(concurrencyLevel), 用作segment[]长度
        while (ssize < concurrencyLevel) {
            ++sshift;
            ssize <<= 1;
        }
        this.segmentShift = 32 - sshift;// 32-幂次sshift, 得到剩余高位的位数, 在(hash(key) >>> segmentShift) & segmentMask中, 用来判断key到底落在哪个segment上
        this.segmentMask = ssize - 1;// ssize-1, 得到segment[]长度的掩码, 在(hash(key) >>> segmentShift) & segmentMask中, 用来判断key到底落在哪个segment上

        // c向上取整initialCapacity/ssize; cap取最接近c的2次幂, 用作segment中table的长度(至少为2)
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        int c = initialCapacity / ssize;
        if (c * ssize < initialCapacity)
            ++c;
        int cap = MIN_SEGMENT_TABLE_CAPACITY;
        while (cap < c)
            cap <<= 1;

        // create segments and segments[0]
        // 创建第一个segment s0, 其中的散列表table(长度为cap), 并指定负载因子等于传入的负载因子, 阈值等于cap*loadFactor
        Segment<K,V> s0 = new Segment<K,V>(loadFactor, (int)(cap * loadFactor), (HashEntry<K,V>[])new HashEntry[cap]);

        // 构造ssize长度的segment[] ss, 有序地写入段[0], 并更新segments指针
        Segment<K,V>[] ss = (Segment<K,V>[])new Segment[ssize];
        UNSAFE.putOrderedObject(ss, SBASE, s0); // ordered write of segments[0] 段的有序写入[0]
        this.segments = ss;
    }

    /**
     * Creates a new, empty map with the specified initial capacity
     * and load factor and with the default concurrencyLevel (16).
     *
     * @param initialCapacity The implementation performs internal
     * sizing to accommodate this many elements.
     * @param loadFactor  the load factor threshold, used to control resizing.
     * Resizing may be performed when the average number of elements per
     * bin exceeds this threshold.
     * @throws IllegalArgumentException if the initial capacity of
     * elements is negative or the load factor is nonpositive
     *
     * @since 1.6
     */
    // 使用指定的初始容量和负载因子以及默认的 concurrencyLevel (16) 创建一个新的空映射。
    public ConcurrentHashMap(int initialCapacity, float loadFactor) {
        this(initialCapacity, loadFactor, DEFAULT_CONCURRENCY_LEVEL);
    }

    /**
     * Creates a new, empty map with the specified initial capacity,
     * and with default load factor (0.75) and concurrencyLevel (16).
     *
     * @param initialCapacity the initial capacity. The implementation
     * performs internal sizing to accommodate this many elements.
     * @throws IllegalArgumentException if the initial capacity of
     * elements is negative.
     */
    // 使用指定的初始容量、默认负载因子 (0.75) 和 concurrencyLevel (16) 创建一个新的空映射。
    public ConcurrentHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR, DEFAULT_CONCURRENCY_LEVEL);
    }

    /**
     * Creates a new, empty map with a default initial capacity (16),
     * load factor (0.75) and concurrencyLevel (16).
     */
    // 使用默认初始容量 (16)、负载因子 (0.75) 和 concurrencyLevel (16) 创建一个新的空映射。
    public ConcurrentHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR, DEFAULT_CONCURRENCY_LEVEL);
    }

    /**
     * Creates a new map with the same mappings as the given map.
     * The map is created with a capacity of 1.5 times the number
     * of mappings in the given map or 16 (whichever is greater),
     * and a default load factor (0.75) and concurrencyLevel (16).
     *
     * @param m the map
     */
    // 创建一个与给定Map具有相同映射的新Map。该映射的创建容量为给定映射中映射数的1.5倍或16（以较大者为准），以及默认负载因子(0.75)和concurrencyLevel(16)。
    public ConcurrentHashMap(Map<? extends K, ? extends V> m) {
        this(Math.max((int) (m.size() / DEFAULT_LOAD_FACTOR) + 1,
                      DEFAULT_INITIAL_CAPACITY),
             DEFAULT_LOAD_FACTOR, DEFAULT_CONCURRENCY_LEVEL);

        // 添加复制集合的的所有键值对
        putAll(m);
    }

    /**
     * Returns <tt>true</tt> if this map contains no key-value mappings.
     *
     * @return <tt>true</tt> if this map contains no key-value mappings
     */
    public boolean isEmpty() {
        /*
         * Sum per-segment modCounts to avoid mis-reporting when
         * elements are concurrently added and removed in one segment
         * while checking another, in which case the table was never
         * actually empty at any point. (The sum ensures accuracy up
         * through at least 1<<31 per-segment modifications before
         * recheck.)  Methods size() and containsValue() use similar
         * constructions for stability checks.
         */
        long sum = 0L;
        final Segment<K,V>[] segments = this.segments;
        for (int j = 0; j < segments.length; ++j) {
            Segment<K,V> seg = segmentAt(segments, j);
            if (seg != null) {
                if (seg.count != 0)
                    return false;
                sum += seg.modCount;
            }
        }
        if (sum != 0L) { // recheck unless no modifications
            for (int j = 0; j < segments.length; ++j) {
                Segment<K,V> seg = segmentAt(segments, j);
                if (seg != null) {
                    if (seg.count != 0)
                        return false;
                    sum -= seg.modCount;
                }
            }
            if (sum != 0L)
                return false;
        }
        return true;
    }

    /**
     * 20210703
     * 返回此映射中键值映射的数量。 如果Map包含多个Integer.MAX_VALUE 元素，则返回 Integer.MAX_VALUE。
     */
    /**
     * Returns the number of key-value mappings in this map.  If the
     * map contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of key-value mappings in this map
     */
    // 返回此映射中键值映射的数量, 如果Map包含多个Integer.MAX_VALUE元素, 则只返回Integer.MAX_VALUE: 先不加锁统计2次, 如果前2次统计到的修改模数不等, 说明发生了并发修改, 则创建每个Segment并加锁统计
    public int size() {
        // 尝试几次以获得准确的计数。 如果由于表中的持续异步更改而失败，则求助于锁定。
        // Try a few times to get accurate count. On failure due to
        // continuous async changes in table, resort to locking.
        // 段数组segments, ConcurrentHashMap实际大小size, int数值是否溢出overflow, 每次统计的修改模数sum, 上一次的修改模数last, 自旋retries
        final Segment<K,V>[] segments = this.segments;
        int size;
        boolean overflow; // true if size overflows 32 bits 如果大小溢出 32 位，则为 true
        long sum;         // sum of modCounts modCounts 的总和
        long last = 0L;   // previous sum 上一笔
        int retries = -1; // first iteration isn't retry 第一次迭代不是重试

        try {
            // 开始自旋
            for (;;) {
                // 如果自旋次数达到加锁前最大自旋次数2, 说明前2次的统计模数发生了变化, 此时需要加锁统计了, 则遍历segments, 对于j段如果不存在, 则创建它并通过CAS记录在segment[j]中, 并阻塞式地对j段Segment进行加锁
                if (retries++ == RETRIES_BEFORE_LOCK) {
                    for (int j = 0; j < segments.length; ++j)
                        ensureSegment(j).lock(); // force creation
                }

                // 初始化每次的修改模数统计sum，实际大小size，是否溢出overflow
                sum = 0L;
                size = 0;
                overflow = false;

                // 如果segment都加锁完毕后, 则再次遍历每一段Segment
                for (int j = 0; j < segments.length; ++j) {
                    // 获取主内存的Segment[j]对象seg
                    Segment<K,V> seg = segmentAt(segments, j);

                    // 如果esg不为null, 说明其上的散列表元素需要做统计, 统计其修改模数sum, 统计其实际大小c, size叠加每个c的结果
                    if (seg != null) {
                        sum += seg.modCount;
                        int c = seg.count;

                        // 如果最高位为1, 此时为负数, 小于0, 说明溢出了
                        if (c < 0 || (size += c) < 0)
                            overflow = true;
                    }
                }

                // 如果统计两次, 修改模数都相等, 说明size统计正确, 则结束自旋
                if (sum == last)
                    break;

                // 如果修改模数不相等, 或者还没统计两次以上, 则需要再次统计
                last = sum;
            }
        } finally {
            // 统计完毕, 如果自旋次数有大于2, 说明每段Segment都上了锁, 则释放每段Segment的锁
            if (retries > RETRIES_BEFORE_LOCK) {
                for (int j = 0; j < segments.length; ++j)
                    segmentAt(segments, j).unlock();
            }
        }

        // 如果int值溢出, 则返回MAX_VALUE, 否则返回正常的统计结果size
        return overflow ? Integer.MAX_VALUE : size;
    }

    /**
     * 20210703
     * A. 返回指定键映射到的值，如果此映射不包含键的映射，则返回 {@code null}。
     * B. 更正式地说，如果此映射包含从键 {@code k} 到值 {@code v} 的映射，使得 {@code key.equals(k)}，则此方法返回 {@code v}； 否则返回 {@code null}。 （最多可以有一个这样的映射。）
     */
    /**
     * A.
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * B.
     * <p>More formally, if this map contains a mapping from a key
     * {@code k} to a value {@code v} such that {@code key.equals(k)},
     * then this method returns {@code v}; otherwise it returns
     * {@code null}.  (There can be at most one such mapping.)
     *
     * @throws NullPointerException if the specified key is null
     */
    // 获取指定键映射到的值，如果此映射不包含键的映射，则返回 {@code null}
    public V get(Object key) {
        Segment<K,V> s; // manually integrate access methods to reduce overhead 手动集成访问方法以减少开销
        HashEntry<K,V>[] tab;

        // HashCode扰动函数, key为String类型则返回key的32位哈希码即可, 否则哈希种子异或k的散列码, 再把结果高位的特征和低位的特征组合起来，降低哈希冲突的概率，也就是说，尽量做到任何一位的变化都能对最终得到的结果产生影响
        int h = hash(key);

        // 获取h对应Segment[]中的对象偏移u, 根据u获取主内存中的Segment对象s
        long u = (((h >>> segmentShift) & segmentMask) << SSHIFT) + SBASE;
        if ((s = (Segment<K,V>)UNSAFE.getObjectVolatile(segments, u)) != null && (tab = s.table) != null) {
            // 然后根据h对应s中散列表tab中的对象偏移, 获取主内存中的HashEntry结点e, 遍历e链表
            for (HashEntry<K,V> e = (HashEntry<K,V>) UNSAFE.getObjectVolatile(tab, ((long)(((tab.length - 1) & h)) << TSHIFT) + TBASE); e != null; e = e.next) {
                K k;

                // 如果e键等于key, 或者e的hash值等于h且e键equals k, 说明e结点就是要找的结点, 则返回e值即可
                if ((k = e.key) == key || (e.hash == h && key.equals(k)))
                    return e.value;
            }
        }

        // 确认没找到key对应的结点, 则返回null
        return null;
    }

    /**
     * Tests if the specified object is a key in this table.
     *
     * @param  key   possible key
     * @return <tt>true</tt> if and only if the specified object
     *         is a key in this table, as determined by the
     *         <tt>equals</tt> method; <tt>false</tt> otherwise.
     * @throws NullPointerException if the specified key is null
     */
    @SuppressWarnings("unchecked")
    public boolean containsKey(Object key) {
        Segment<K,V> s; // same as get() except no need for volatile value read
        HashEntry<K,V>[] tab;
        int h = hash(key);
        long u = (((h >>> segmentShift) & segmentMask) << SSHIFT) + SBASE;
        if ((s = (Segment<K,V>)UNSAFE.getObjectVolatile(segments, u)) != null &&
            (tab = s.table) != null) {
            for (HashEntry<K,V> e = (HashEntry<K,V>) UNSAFE.getObjectVolatile
                     (tab, ((long)(((tab.length - 1) & h)) << TSHIFT) + TBASE);
                 e != null; e = e.next) {
                K k;
                if ((k = e.key) == key || (e.hash == h && key.equals(k)))
                    return true;
            }
        }
        return false;
    }

    /**
     * Returns <tt>true</tt> if this map maps one or more keys to the
     * specified value. Note: This method requires a full internal
     * traversal of the hash table, and so is much slower than
     * method <tt>containsKey</tt>.
     *
     * @param value value whose presence in this map is to be tested
     * @return <tt>true</tt> if this map maps one or more keys to the
     *         specified value
     * @throws NullPointerException if the specified value is null
     */
    public boolean containsValue(Object value) {
        // Same idea as size()
        if (value == null)
            throw new NullPointerException();
        final Segment<K,V>[] segments = this.segments;
        boolean found = false;
        long last = 0;
        int retries = -1;
        try {
            outer: for (;;) {
                if (retries++ == RETRIES_BEFORE_LOCK) {
                    for (int j = 0; j < segments.length; ++j)
                        ensureSegment(j).lock(); // force creation
                }
                long hashSum = 0L;
                int sum = 0;
                for (int j = 0; j < segments.length; ++j) {
                    HashEntry<K,V>[] tab;
                    Segment<K,V> seg = segmentAt(segments, j);
                    if (seg != null && (tab = seg.table) != null) {
                        for (int i = 0 ; i < tab.length; i++) {
                            HashEntry<K,V> e;
                            for (e = entryAt(tab, i); e != null; e = e.next) {
                                V v = e.value;
                                if (v != null && value.equals(v)) {
                                    found = true;
                                    break outer;
                                }
                            }
                        }
                        sum += seg.modCount;
                    }
                }
                if (retries > 0 && sum == last)
                    break;
                last = sum;
            }
        } finally {
            if (retries > RETRIES_BEFORE_LOCK) {
                for (int j = 0; j < segments.length; ++j)
                    segmentAt(segments, j).unlock();
            }
        }
        return found;
    }

    /**
     * Legacy method testing if some key maps into the specified value
     * in this table.  This method is identical in functionality to
     * {@link #containsValue}, and exists solely to ensure
     * full compatibility with class {@link java.util.Hashtable},
     * which supported this method prior to introduction of the
     * Java Collections framework.

     * @param  value a value to search for
     * @return <tt>true</tt> if and only if some key maps to the
     *         <tt>value</tt> argument in this table as
     *         determined by the <tt>equals</tt> method;
     *         <tt>false</tt> otherwise
     * @throws NullPointerException if the specified value is null
     */
    public boolean contains(Object value) {
        return containsValue(value);
    }

    /**
     * 20210702
     * A. 将指定的键映射到此表中的指定值。 键和值都不能为空。
     * B. 可以通过使用等于原始键的键调用 get 方法来检索该值。
     */
    /**
     * A.
     * Maps the specified key to the specified value in this table.
     * Neither the key nor the value can be null.
     *
     * B.
     * <p> The value can be retrieved by calling the <tt>get</tt> method
     * with a key that is equal to the original key.
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with <tt>key</tt>, or
     *         <tt>null</tt> if there was no mapping for <tt>key</tt>
     * @throws NullPointerException if the specified key or value is null
     */
    // 根据key的hash值选择segment后, 获取当前segment锁后, 往其散列表添加key-value条目(允许值替换), 并且如果插入后散列表实际大小超过阈值, 则还会发生扩容
    @SuppressWarnings("unchecked")
    public V put(K key, V value) {
        Segment<K,V> s;
        if (value == null) throw new NullPointerException();

        // HashCode扰动函数, key为String类型则返回key的32位哈希码即可, 否则哈希种子异或k的散列码, 再把结果高位的特征和低位的特征组合起来，降低哈希冲突的概率，也就是说，尽量做到任何一位的变化都能对最终得到的结果产生影响
        int hash = hash(key);

        // 相当于取hash的高ssize位数值 % (ssize-1)，得到hash在segment中的索引
        int j = (hash >>> segmentShift) & segmentMask;

        // 获取索引元素，通过UNSAFE方法，直接获取Segment[]中第j个对象
        if ((s = (Segment<K,V>)UNSAFE.getObject          // nonvolatile; recheck 非易失性； 复查
             (segments, (j << SSHIFT) + SBASE)) == null) //  in ensureSegment 在确保段
            // 如果第j个对象为null, 则创建它并通过CAS记录在segment[j]中
            s = ensureSegment(j);

        // 获取第j个Segment后, 则获取当前segment锁后, 往其散列表添加key-value条目, onlyIfAbsent为true代表只允许key对应结点不存在时才添加, 为false代表key对应结点已存在时可以发生值替换; 如果插入后散列表实际大小超过阈值, 则还会发生扩容
        return s.put(key, hash, value, false);
    }

    /**
     * {@inheritDoc}
     *
     * @return the previous value associated with the specified key,
     *         or <tt>null</tt> if there was no mapping for the key
     * @throws NullPointerException if the specified key or value is null
     */
    // 根据key的hash值选择segment后, 获取当前segment锁后, 往其散列表添加key-value条目(不允许值替换), 并且如果插入后散列表实际大小超过阈值, 则还会发生扩容
    @SuppressWarnings("unchecked")
    public V putIfAbsent(K key, V value) {
        Segment<K,V> s;
        if (value == null)
            throw new NullPointerException();

        // HashCode扰动函数, key为String类型则返回key的32位哈希码即可, 否则哈希种子异或k的散列码, 再把结果高位的特征和低位的特征组合起来，降低哈希冲突的概率，也就是说，尽量做到任何一位的变化都能对最终得到的结果产生影响
        int hash = hash(key);

        // 相当于取hash的高ssize位数值 % (ssize-1)，得到hash在segment中的索引
        int j = (hash >>> segmentShift) & segmentMask;

        // 获取索引元素，通过UNSAFE方法，直接获取Segment[]中第j个对象
        if ((s = (Segment<K,V>)UNSAFE.getObject(segments, (j << SSHIFT) + SBASE)) == null)
            // 如果第j个对象为null, 则创建它并通过CAS记录在segment[j]中
            s = ensureSegment(j);

        // 获取第j个Segment后, 则获取当前segment锁后, 往其散列表添加key-value条目, onlyIfAbsent为true代表只允许key对应结点不存在时才添加, 为false代表key对应结点已存在时可以发生值替换; 如果插入后散列表实际大小超过阈值, 则还会发生扩容
        return s.put(key, hash, value, true);
    }

    /**
     * Copies all of the mappings from the specified map to this one.
     * These mappings replace any mappings that this map had for any of the
     * keys currently in the specified map.
     *
     * @param m mappings to be stored in this map
     */
    // 添加复制集合的的所有键值对
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet())
            // 根据key的hash值选择segment后, 获取当前segment锁后, 往其散列表添加key-value条目(允许值替换), 并且如果插入后散列表实际大小超过阈值, 则还会发生扩容
            put(e.getKey(), e.getValue());
    }

    /**
     * 20210703
     * 从此映射中删除键（及其对应的值）。如果键不在映射中，则此方法不执行任何操作。
     */
    /**
     * Removes the key (and its corresponding value) from this map.
     * This method does nothing if the key is not in the map.
     *
     * @param  key the key that needs to be removed
     * @return the previous value associated with <tt>key</tt>, or
     *         <tt>null</tt> if there was no mapping for <tt>key</tt>
     * @throws NullPointerException if the specified key is null
     */
    // 如果key存在，则从此映射中删除该key对应的映射，忽略value值
    public V remove(Object key) {
        // HashCode扰动函数, key为String类型则返回key的32位哈希码即可, 否则哈希种子异或k的散列码, 再把结果高位的特征和低位的特征组合起来，降低哈希冲突的概率，也就是说，尽量做到任何一位的变化都能对最终得到的结果产生影响
        int hash = hash(key);

        // 获取给定hash值的段
        Segment<K,V> s = segmentForHash(hash);

        // 获取当前segment锁后, 删除散列表中指定key对应的结点, 删除成功则返回旧值, 否则返回null
        return s == null ? null : s.remove(key, hash, null);
    }

    /**
     * {@inheritDoc}
     *
     * @throws NullPointerException if the specified key is null
     */
    // 删除key和value都equals的键值对，value值必须匹配
    public boolean remove(Object key, Object value) {
        // HashCode扰动函数, key为String类型则返回key的32位哈希码即可, 否则哈希种子异或k的散列码, 再把结果高位的特征和低位的特征组合起来，降低哈希冲突的概率，也就是说，尽量做到任何一位的变化都能对最终得到的结果产生影响
        int hash = hash(key);
        Segment<K,V> s;

        // 获取给定hash值的段, 获取当前segment锁后, 删除散列表key、value都匹配的结点, 删除成功则返回旧值, 否则返回null
        return value != null && (s = segmentForHash(hash)) != null && s.remove(key, hash, value) != null;
    }

    /**
     * {@inheritDoc}
     *
     * @throws NullPointerException if any of the arguments are null
     */
    public boolean replace(K key, V oldValue, V newValue) {
        int hash = hash(key);
        if (oldValue == null || newValue == null)
            throw new NullPointerException();
        Segment<K,V> s = segmentForHash(hash);
        return s != null && s.replace(key, hash, oldValue, newValue);
    }

    /**
     * {@inheritDoc}
     *
     * @return the previous value associated with the specified key,
     *         or <tt>null</tt> if there was no mapping for the key
     * @throws NullPointerException if the specified key or value is null
     */
    public V replace(K key, V value) {
        int hash = hash(key);
        if (value == null)
            throw new NullPointerException();
        Segment<K,V> s = segmentForHash(hash);
        return s == null ? null : s.replace(key, hash, value);
    }

    /**
     * Removes all of the mappings from this map.
     */
    public void clear() {
        final Segment<K,V>[] segments = this.segments;
        for (int j = 0; j < segments.length; ++j) {
            Segment<K,V> s = segmentAt(segments, j);
            if (s != null)
                s.clear();
        }
    }

    /**
     * Returns a {@link Set} view of the keys contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  The set supports element
     * removal, which removes the corresponding mapping from this map,
     * via the <tt>Iterator.remove</tt>, <tt>Set.remove</tt>,
     * <tt>removeAll</tt>, <tt>retainAll</tt>, and <tt>clear</tt>
     * operations.  It does not support the <tt>add</tt> or
     * <tt>addAll</tt> operations.
     *
     * <p>The view's <tt>iterator</tt> is a "weakly consistent" iterator
     * that will never throw {@link ConcurrentModificationException},
     * and guarantees to traverse elements as they existed upon
     * construction of the iterator, and may (but is not guaranteed to)
     * reflect any modifications subsequent to construction.
     */
    public Set<K> keySet() {
        Set<K> ks = keySet;
        return (ks != null) ? ks : (keySet = new KeySet());
    }

    /**
     * Returns a {@link Collection} view of the values contained in this map.
     * The collection is backed by the map, so changes to the map are
     * reflected in the collection, and vice-versa.  The collection
     * supports element removal, which removes the corresponding
     * mapping from this map, via the <tt>Iterator.remove</tt>,
     * <tt>Collection.remove</tt>, <tt>removeAll</tt>,
     * <tt>retainAll</tt>, and <tt>clear</tt> operations.  It does not
     * support the <tt>add</tt> or <tt>addAll</tt> operations.
     *
     * <p>The view's <tt>iterator</tt> is a "weakly consistent" iterator
     * that will never throw {@link ConcurrentModificationException},
     * and guarantees to traverse elements as they existed upon
     * construction of the iterator, and may (but is not guaranteed to)
     * reflect any modifications subsequent to construction.
     */
    public Collection<V> values() {
        Collection<V> vs = values;
        return (vs != null) ? vs : (values = new Values());
    }

    /**
     * Returns a {@link Set} view of the mappings contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  The set supports element
     * removal, which removes the corresponding mapping from the map,
     * via the <tt>Iterator.remove</tt>, <tt>Set.remove</tt>,
     * <tt>removeAll</tt>, <tt>retainAll</tt>, and <tt>clear</tt>
     * operations.  It does not support the <tt>add</tt> or
     * <tt>addAll</tt> operations.
     *
     * <p>The view's <tt>iterator</tt> is a "weakly consistent" iterator
     * that will never throw {@link ConcurrentModificationException},
     * and guarantees to traverse elements as they existed upon
     * construction of the iterator, and may (but is not guaranteed to)
     * reflect any modifications subsequent to construction.
     */
    public Set<Map.Entry<K,V>> entrySet() {
        Set<Map.Entry<K,V>> es = entrySet;
        return (es != null) ? es : (entrySet = new EntrySet());
    }

    /**
     * Returns an enumeration of the keys in this table.
     *
     * @return an enumeration of the keys in this table
     * @see #keySet()
     */
    public Enumeration<K> keys() {
        return new KeyIterator();
    }

    /**
     * Returns an enumeration of the values in this table.
     *
     * @return an enumeration of the values in this table
     * @see #values()
     */
    public Enumeration<V> elements() {
        return new ValueIterator();
    }

    /* ---------------- Iterator Support -------------- */

    abstract class HashIterator {
        int nextSegmentIndex;// Segment[]索引
        int nextTableIndex;// Segment#HashEntry[]索引
        HashEntry<K,V>[] currentTable;// Segment#HashEntry[]
        HashEntry<K, V> nextEntry;// 缓存下一个要返回的结点
        HashEntry<K, V> lastReturned;// 当前返回的结点

        HashIterator() {
            nextSegmentIndex = segments.length - 1;
            nextTableIndex = -1;
            advance();// 缓存下一个要返回的结点
        }

        /**
         * 20210703
         * 将 nextEntry 设置为下一个非空表的第一个节点（按倒序排列，以简化检查）。
         */
        /**
         * Set nextEntry to first node of next non-empty table
         * (in backwards order, to simplify checks).
         */
        // 缓存下一个要返回的结点
        final void advance() {
            for (;;) {
                // 倒序遍历Segment中的散列表
                if (nextTableIndex >= 0) {
                    if ((nextEntry = entryAt(currentTable, nextTableIndex--)) != null)
                        break;
                }
                // 倒序遍历Segment[]
                else if (nextSegmentIndex >= 0) {
                    Segment<K,V> seg = segmentAt(segments, nextSegmentIndex--);
                    if (seg != null && (currentTable = seg.table) != null)
                        nextTableIndex = currentTable.length - 1;
                }
                else
                    break;
            }
        }

        // 返回nextEntry
        final HashEntry<K,V> nextEntry() {
            HashEntry<K,V> e = nextEntry;
            if (e == null)
                throw new NoSuchElementException();
            lastReturned = e; // cannot assign until after null check
            if ((nextEntry = e.next) == null)
                advance();// 缓存下一个要返回的结点
            return e;
        }

        public final boolean hasNext() { return nextEntry != null; }
        public final boolean hasMoreElements() { return nextEntry != null; }

        public final void remove() {
            if (lastReturned == null)
                throw new IllegalStateException();
            ConcurrentHashMap.this.remove(lastReturned.key);
            lastReturned = null;
        }
    }

    final class KeyIterator extends HashIterator implements Iterator<K>, Enumeration<K>
    {
        public final K next()        { return super.nextEntry().key; }
        public final K nextElement() { return super.nextEntry().key; }
    }

    final class ValueIterator extends HashIterator implements Iterator<V>, Enumeration<V>
    {
        public final V next()        { return super.nextEntry().value; }
        public final V nextElement() { return super.nextEntry().value; }
    }

    /**
     * Custom Entry class used by EntryIterator.next(), that relays
     * setValue changes to the underlying map.
     */
    // EntryIterator.next() 使用的自定义条目类，它将 setValue 更改中继到底层映射。
    final class WriteThroughEntry extends AbstractMap.SimpleEntry<K,V>
    {
        WriteThroughEntry(K k, V v) {
            super(k,v);
        }

        /**
         * 20210703
         * 设置我们条目的值并写入Map。 这里要返回的值有点随意。 由于WriteThroughEntry 不一定跟踪异步更改，
         * 因此最近的“先前”值可能与我们返回的值不同（或者甚至可能已被删除，在这种情况下，put 将重新建立）。 我们没有也不能保证更多。
         */
        /**
         * Set our entry's value and write through to the map. The
         * value to return is somewhat arbitrary here. Since a
         * WriteThroughEntry does not necessarily track asynchronous
         * changes, the most recent "previous" value could be
         * different from what we return (or could even have been
         * removed in which case the put will re-establish). We do not
         * and cannot guarantee more.
         */
        public V setValue(V value) {
            if (value == null) throw new NullPointerException();
            V v = super.setValue(value);
            ConcurrentHashMap.this.put(getKey(), value);
            return v;
        }
    }

    final class EntryIterator extends HashIterator implements Iterator<Entry<K,V>>
    {
        public Map.Entry<K,V> next() {
            HashEntry<K,V> e = super.nextEntry();
            return new WriteThroughEntry(e.key, e.value);
        }
    }

    final class KeySet extends AbstractSet<K> {
        public Iterator<K> iterator() {
            return new KeyIterator();
        }
        public int size() {
            return ConcurrentHashMap.this.size();
        }
        public boolean isEmpty() {
            return ConcurrentHashMap.this.isEmpty();
        }
        public boolean contains(Object o) {
            return ConcurrentHashMap.this.containsKey(o);
        }
        public boolean remove(Object o) {
            return ConcurrentHashMap.this.remove(o) != null;
        }
        public void clear() {
            ConcurrentHashMap.this.clear();
        }
    }

    final class Values extends AbstractCollection<V> {
        public Iterator<V> iterator() {
            return new ValueIterator();
        }
        public int size() {
            return ConcurrentHashMap.this.size();
        }
        public boolean isEmpty() {
            return ConcurrentHashMap.this.isEmpty();
        }
        public boolean contains(Object o) {
            return ConcurrentHashMap.this.containsValue(o);
        }
        public void clear() {
            ConcurrentHashMap.this.clear();
        }
    }

    final class EntrySet extends AbstractSet<Map.Entry<K,V>> {
        public Iterator<Map.Entry<K,V>> iterator() {
            return new EntryIterator();
        }
        public boolean contains(Object o) {
            if (!(o instanceof Map.Entry))
                return false;
            Map.Entry<?,?> e = (Map.Entry<?,?>)o;
            V v = ConcurrentHashMap.this.get(e.getKey());
            return v != null && v.equals(e.getValue());
        }
        public boolean remove(Object o) {
            if (!(o instanceof Map.Entry))
                return false;
            Map.Entry<?,?> e = (Map.Entry<?,?>)o;
            return ConcurrentHashMap.this.remove(e.getKey(), e.getValue());
        }
        public int size() {
            return ConcurrentHashMap.this.size();
        }
        public boolean isEmpty() {
            return ConcurrentHashMap.this.isEmpty();
        }
        public void clear() {
            ConcurrentHashMap.this.clear();
        }
    }

    /* ---------------- Serialization Support -------------- */

    /**
     * Save the state of the <tt>ConcurrentHashMap</tt> instance to a
     * stream (i.e., serialize it).
     * @param s the stream
     * @serialData
     * the key (Object) and value (Object)
     * for each key-value mapping, followed by a null pair.
     * The key-value mappings are emitted in no particular order.
     */
    private void writeObject(java.io.ObjectOutputStream s) throws IOException {
        // force all segments for serialization compatibility
        for (int k = 0; k < segments.length; ++k)
            ensureSegment(k);
        s.defaultWriteObject();

        final Segment<K,V>[] segments = this.segments;
        for (int k = 0; k < segments.length; ++k) {
            Segment<K,V> seg = segmentAt(segments, k);
            seg.lock();
            try {
                HashEntry<K,V>[] tab = seg.table;
                for (int i = 0; i < tab.length; ++i) {
                    HashEntry<K,V> e;
                    for (e = entryAt(tab, i); e != null; e = e.next) {
                        s.writeObject(e.key);
                        s.writeObject(e.value);
                    }
                }
            } finally {
                seg.unlock();
            }
        }
        s.writeObject(null);
        s.writeObject(null);
    }

    /**
     * Reconstitute the <tt>ConcurrentHashMap</tt> instance from a
     * stream (i.e., deserialize it).
     * @param s the stream
     */
    @SuppressWarnings("unchecked")
    private void readObject(java.io.ObjectInputStream s)
        throws IOException, ClassNotFoundException {
        // Don't call defaultReadObject()
        ObjectInputStream.GetField oisFields = s.readFields();
        final Segment<K,V>[] oisSegments = (Segment<K,V>[])oisFields.get("segments", null);

        final int ssize = oisSegments.length;
        if (ssize < 1 || ssize > MAX_SEGMENTS
            || (ssize & (ssize-1)) != 0 )  // ssize not power of two
            throw new java.io.InvalidObjectException("Bad number of segments:"
                                                     + ssize);
        int sshift = 0, ssizeTmp = ssize;
        while (ssizeTmp > 1) {
            ++sshift;
            ssizeTmp >>>= 1;
        }
        UNSAFE.putIntVolatile(this, SEGSHIFT_OFFSET, 32 - sshift);
        UNSAFE.putIntVolatile(this, SEGMASK_OFFSET, ssize - 1);
        UNSAFE.putObjectVolatile(this, SEGMENTS_OFFSET, oisSegments);

        // set hashMask
        UNSAFE.putIntVolatile(this, HASHSEED_OFFSET, randomHashSeed(this));

        // Re-initialize segments to be minimally sized, and let grow.
        int cap = MIN_SEGMENT_TABLE_CAPACITY;
        final Segment<K,V>[] segments = this.segments;
        for (int k = 0; k < segments.length; ++k) {
            Segment<K,V> seg = segments[k];
            if (seg != null) {
                seg.threshold = (int)(cap * seg.loadFactor);
                seg.table = (HashEntry<K,V>[]) new HashEntry[cap];
            }
        }

        // Read the keys and values, and put the mappings in the table
        for (;;) {
            K key = (K) s.readObject();
            V value = (V) s.readObject();
            if (key == null)
                break;
            put(key, value);
        }
    }

    // Unsafe mechanics
    private static final sun.misc.Unsafe UNSAFE;
    private static final long SBASE;// Segment[]数组起始偏移, ConcurrentHashMap中的segments
    private static final int SSHIFT;// numberOfLeadingZeros得到ss高位1前的0的位数, 31-numberOfLeadingZeros得到ss高位1后的位数 => 如果ss为2的幂次数, 则J << SSHIFT + BASE 等价于 j * ss + BASE
    private static final long TBASE;// HashEntry[]数组起始偏移, 每个Segement中的table
    private static final int TSHIFT;// numberOfLeadingZeros得到ss高位1前的0的位数, 31-numberOfLeadingZeros得到ss高位1后的位数 => 如果ss为2的幂次数, 则J << SSHIFT + BASE 等价于 j * ss + BASE
    private static final long HASHSEED_OFFSET;// hashSeed, 哈希种子
    private static final long SEGSHIFT_OFFSET;// segmentShift, 用来判断key到底落在哪个segment上
    private static final long SEGMASK_OFFSET;// segmentMask, 用来判断key到底落在哪个segment上
    private static final long SEGMENTS_OFFSET;// segments, Segment[]

    static {
        int ss, ts;
        try {
            UNSAFE = sun.misc.Unsafe.getUnsafe();
            Class tc = HashEntry[].class;
            Class sc = Segment[].class;
            TBASE = UNSAFE.arrayBaseOffset(tc);// HashEntry[]数组起始偏移, 每个Segement中的table
            SBASE = UNSAFE.arrayBaseOffset(sc);// Segment[]数组起始偏移, ConcurrentHashMap中的segments
            ts = UNSAFE.arrayIndexScale(tc);// 获取数组每个块的内存大小, base+0*ns得到第0块内存的起始地址, base+1*ns得到第1块内存的起始地址
            ss = UNSAFE.arrayIndexScale(sc);// 获取数组每个块的内存大小, base+0*ns得到第0块内存的起始地址, base+1*ns得到第1块内存的起始地址
            HASHSEED_OFFSET = UNSAFE.objectFieldOffset(
                ConcurrentHashMap.class.getDeclaredField("hashSeed"));
            SEGSHIFT_OFFSET = UNSAFE.objectFieldOffset(
                ConcurrentHashMap.class.getDeclaredField("segmentShift"));
            SEGMASK_OFFSET = UNSAFE.objectFieldOffset(
                ConcurrentHashMap.class.getDeclaredField("segmentMask"));
            SEGMENTS_OFFSET = UNSAFE.objectFieldOffset(
                ConcurrentHashMap.class.getDeclaredField("segments"));
        } catch (Exception e) {
            throw new Error(e);
        }
        if ((ss & (ss-1)) != 0 || (ts & (ts-1)) != 0)
            throw new Error("data type scale not a power of two");
        SSHIFT = 31 - Integer.numberOfLeadingZeros(ss);// numberOfLeadingZeros得到ss高位1前的0的位数, 31-numberOfLeadingZeros得到ss高位1后的位数
        TSHIFT = 31 - Integer.numberOfLeadingZeros(ts);// numberOfLeadingZeros得到ts高位1前的0的位数, 31-numberOfLeadingZeros得到ts高位1后的位数
    }

}
