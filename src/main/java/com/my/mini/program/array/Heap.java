package com.my.mini.program.array;

/**
 * @author creditease
 *
 *
 * 堆排序
 */
public class Heap {





    public static void heap(int [] arr,int size){
        if(size==0 || arr==null){
            return;
        }
        for(int i=size/2;i>0;i--){
            shit(arr,i,size-1);
        }
    }

    public static void shit(int[] data,int i,int size){
        int j,tmp,post;
        j=2*i;
        tmp=data[i];
        post=0;
        while(j<=size && post==0)
        {
            if(j<size)
            {
                //小顶堆的比较
                //if(data[j]>data[j+1])
                //找出两个子节点最大值
                if(data[j]<data[j+1]){
                    j++;
                }
            }
            //小顶堆的比较
            //if(tmp<=data[j])
            //若树根较大，结束比较过程
            if(tmp>=data[j])    {
                post=1;
            } else {
                //若树根较小，则继续比较，这里将最大子节点赋值给父节点
                data[j/2]=data[j];
                j=2*j;
            }
        }
        //指定树根为父节点
        data[j/2]=tmp;
    }



    public static void  main(String [] args){

        int[] data={10,30,20,6,17,19,18,15,4,9,11};

        adjustOriginArray(data,11);

        for(int m:data){
            System.out.println(m);
        }



//        int[] rst=new int[4];
//        for(int i=0;i<4;i++){
//            insertHeap(rst,data[i],i);
//        }
//        for(int m:rst){
//            System.out.println(m);
//        }
//        for(int i=4;i<data.length;i++){
//            if(data[i]<rst[0]){
//                rst[0]=data[i];
//                adjustHeap(rst,0,4);
//                System.out.println("===============");
//                for(int m:rst){
//                    System.out.println(m);
//                }
//            }
//
//        }



    }



    public static void insertHeap(int[] arr,int value,int index){
        arr[index]=value;
        while (index!=0){
            int p=(index-1)/2;
            if(arr[p]<arr[index]){
                swap(arr,p,index);
                index=p;
            }else {
                break;
            }
        }
    }


    public static void adjustHeap(int[] arr,int index,int size){
        int left=2*index+1;
        int right=2*index+2;
        int largest=index;
        while (left<size){
            if(arr[left]>arr[index]){
                largest=left;
            }
            if(right<size && arr[right]>arr[largest]){
                largest=right;
            }
            if(largest!=index){
                swap(arr,largest,index);
            }else {
                break;
            }
            index=largest;
            left=2*index+1;
            right=2*index+2;
        }
    }


    public static void adjustOriginArray(int[] arr,int size){
        for(int i=size/2;i>=0;i--){
            adjustHeap(arr,i,size);
        }
    }




    public static void swap(int[] arr,int p,int index){
        int t=arr[p];
        arr[p]=arr[index];
        arr[index]=t;
    }
}
