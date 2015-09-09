/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssignmentOne;

/**
 * @author Saif Asad
 * @author Mark Manson
 */
public class Sort <E extends Comparable<? super E>> 
{
    private E[] data;
    public Sort(E[] data)
    {
        this.data = data;
    }
    public void quickSort()
    {
        quickSortRecursive(0, data.length - 1);
    }
    public void quickSortRecursive(int left, int right)
    {
        int pivot;
        if(left >= right)
            return;
        pivot = partition(left, right);
        quickSortRecursive(left, pivot - 1);
        quickSortRecursive(pivot+1, right);
    }
    
    public int partition(int left, int right)
    {
        while(true)
        {
            while(left < right && data[left].compareTo(data[right]) < 0)
            {
                right--;
            }
            if(left < right)
            {
                swap(left++, right);
            }
            else
            {
                return left;
            }
            while(left < right && data[left].compareTo(data[right] )< 0)
            {
                left++;
            }
            if(left < right)
            {
                swap(left, right--);
            }
            else
            {
                return right;
            }
        }
    }
    public void swap(int i, int j)
    {
        E temp  = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
    
}

