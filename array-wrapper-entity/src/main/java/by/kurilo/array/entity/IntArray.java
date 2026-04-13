package by.kurilo.array.entity;

import java.util.Arrays;
import java.util.StringJoiner;

public class IntArray {
    private int[] array;

    public IntArray(int... values){
        this.array = Arrays.copyOf(values,values.length);
    }
    public int length() {
        return array.length;
    }

    public int get (int index){
        return array[index];
    }

    public void set(int index,int value){
        array[index]=value;
    }

    public int[] toArray(){
        return Arrays.copyOf(array,array.length);
    }
    @Override
    public String toString() {
        return new StringJoiner(", ", IntArray.class.getSimpleName() + "[", "]")
                .add("array=" + Arrays.toString(array))
                .toString();
    }
}
