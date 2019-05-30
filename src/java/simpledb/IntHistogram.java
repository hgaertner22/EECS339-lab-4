package simpledb;

/** A class to represent a fixed-width histogram over a single integer-based field.
 */
public class IntHistogram {

    public int numBuckets;
    public int min;
    public int max;
    private int[] buckets;
    private int bucketSize;
    private int ntups;


    /**
     * Create a new IntHistogram.
     * 
     * This IntHistogram should maintain a histogram of integer values that it receives.
     * It should split the histogram into "buckets" buckets.
     * 
     * The values that are being histogrammed will be provided one-at-a-time through the "addValue()" function.
     * 
     * Your implementation should use space and have execution time that are both
     * constant with respect to the number of values being histogrammed.  For example, you shouldn't 
     * simply store every value that you see in a sorted list.
     * 
     * @param buckets The number of buckets to split the input value into.
     * @param min The minimum integer value that will ever be passed to this class for histogramming
     * @param max The maximum integer value that will ever be passed to this class for histogramming
     */




    public IntHistogram(int buckets, int min, int max) {
    	// some code goes here
        this.numBuckets=buckets;
        this.min=min;
        this.max=max;
        this.buckets=new int[numBuckets];
        this.bucketSize=(int) Math.ceil(((double) (this.max-this.min+1))/numBuckets);
        this.ntups=0;
    }

    /**
     * Add a value to the set of values that you are keeping a histogram of.
     * @param v Value to add to the histogram
     */
    public void addValue(int v) {
    	// some code goes here
        int i=(v-this.min)/this.bucketSize;
        buckets[i]++;
        this.ntups++;
    }

    /**
     * Estimate the selectivity of a particular predicate and operand on this table.
     * 
     * For example, if "op" is "GREATER_THAN" and "v" is 5, 
     * return your estimate of the fraction of elements that are greater than 5.
     * 
     * @param op Operator
     * @param v Value
     * @return Predicted selectivity of this particular operator and value
     */
    public double estimateSelectivity(Predicate.Op op, int v) {
        // some code goes here
        if(v>this.max) {
            if(op==Predicate.Op.EQUALS || op==Predicate.Op.GREATER_THAN) {
                return 0;
            }
            if(op==Predicate.Op.GREATER_THAN_OR_EQ || op==Predicate.Op.LIKE) {
                return 0;
            }
            return 1;
        }

        if(v<this.min) {
            if(op==Predicate.Op.EQUALS || op==Predicate.Op.LESS_THAN)
                return 0;
            if(op==Predicate.Op.LESS_THAN_OR_EQ || op== Predicate.Op.LIKE)
                return 0;
            return 1;
        }

        int i=(v-this.min)/this.bucketSize;
        double w=this.bucketSize;
        double h=buckets[i];

    	if (op==Predicate.Op.EQUALS) {
            return h/w/this.ntups;
        }
        else if (op==Predicate.Op.NOT_EQUALS) {
            return 1-h/w/ntups;
        }

        double fromBuck=h/ntups;
        int bRight= ((int) w)*(i+1)+this.min-1;
        double buckGreat=(bRight-v)/w;
        double buckCont=buckGreat*fromBuck;
        double fromOthers=0;
        for(int j=i+1; j<numBuckets; j++)
            fromOthers=fromOthers+buckets[j];
        fromOthers=fromOthers/ntups;
        double greaterSelec=fromOthers+buckCont;

        if(op== Predicate.Op.GREATER_THAN)
            return greaterSelec;
        else if (op== Predicate.Op.GREATER_THAN_OR_EQ) {
            return greaterSelec+h/w/this.ntups;
        }
        else if (op==Predicate.Op.LESS_THAN_OR_EQ) {
            return 1-greaterSelec;
        }
        else if (op==Predicate.Op.LESS_THAN) {
            return 1-greaterSelec-h/w/this.ntups;
        }
        else if (op== Predicate.Op.LIKE) {
            return 1/numBuckets;   // ?
        }
        return -1;
    }
    
    /**
     * @return
     *     the average selectivity of this histogram.
     *     
     *     This is not an indispensable method to implement the basic
     *     join optimization. It may be needed if you want to
     *     implement a more efficient optimization
     * */
    public double avgSelectivity()
    {
        // some code goes here
        return 1.0;
    }
    
    /**
     * @return A string describing this histogram, for debugging purposes
     */
    public String toString() {
        String str="";
        for(int i=0; i<numBuckets; i++) {
            str=str+Integer.toString(i)+"= " + Integer.toString(buckets[i])+"; ";
        }
        return str;
    }
}
