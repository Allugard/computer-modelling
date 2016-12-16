package lab4;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by 111 on 15.12.2016.
 */
public class Main {
    static int count=2;
    public static void main(String[] args) {
        int [][] matrix={{Integer.MIN_VALUE, 1, 8, 3},
                          {4,Integer.MIN_VALUE,7,2},
                           {3,6,5,Integer.MIN_VALUE},
                             {10,3,5,Integer.MIN_VALUE}    };
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
        System.out.println();
        String msg="";
        msg=searchWay(matrix,msg);
        System.out.println(msg);
    }

    public static String  searchWay(int [][] matr, String msg) {
        if (count < matr.length) {
            count=count+1;
            int[][] bufMatr = new int[matr.length][matr.length];
            for (int i = 0; i < matr.length; i++) {
                bufMatr[i] = Arrays.copyOf(matr[i], matr.length);
            }
            int[] minVal = searchMaxRow(bufMatr);
            for (int i = 0; i < matr.length; i++) {
                for (int j = 0; j < matr.length; j++) {
                    if (bufMatr[i][j] != Integer.MIN_VALUE) {
                        bufMatr[i][j] -= minVal[i];
                    }
                }
            }
        System.out.println("\nMin:");
        for (int i = 0; i < bufMatr.length; i++) {
            System.out.println(Arrays.toString(bufMatr[i]));
        }
            minVal = searchMaxColumn(bufMatr);
            for (int i = 0; i < matr.length; i++) {
                for (int j = 0; j < matr.length; j++) {
                    if (bufMatr[j][i] != Integer.MIN_VALUE) {
                        bufMatr[j][i] -= minVal[i];
                    }
                }
            }
        System.out.println("\nMax:");
        for (int i = 0; i < bufMatr.length; i++) {
            System.out.println(Arrays.toString(bufMatr[i]));
        }
            int[] partOfWay = findCurrrenElement(bufMatr);
//            System.out.println(Arrays.toString(partOfWay));

            msg += partOfWay[0] + "->" + partOfWay[1] + ";\n";
            for (int i = 0; i < matr.length; i++) {
                matr[i][partOfWay[1]] = Integer.MIN_VALUE;
                matr[partOfWay[0]][i] = Integer.MIN_VALUE;
            }
            matr[partOfWay[1]][partOfWay[0]]=Integer.MIN_VALUE;
           /* for (int i = 0; i < bufMatr.length; i++) {
                System.out.println(Arrays.toString(matr[i]));
            }*/

            //System.out.println(partOfWay[0]+"->"+partOfWay[1]);
            msg=searchWay(matr, msg);



        }
        else {
            int [] partOfWay=searchMax(matr);
           // System.out.println(partOfWay[0]+"->"+partOfWay[1]);
            msg+=partOfWay[0]+"->"+partOfWay[1];
        }
        return msg;
    }

    private static int[] findCurrrenElement(int[][] matr) {
        int[]a={0,0};
        int b=Integer.MIN_VALUE;
        for (int i = 0; i <matr.length; i++) {
            for (int j = 0; j <matr.length ; j++) {
                if(matr[i][j]==0&&findDifference(matr,i,j)>b){
                    a[0]=i;
                    a[1]=j;
                    b=findDifference(matr,i,j);
                }
            }

        }
        return a;
    }

    private static int findDifference(int[][] matr, int i, int j) {
        int a=findMaxRow(matr,i,j)+findMaxColumn(matr,j,i);

        return a;
    }

    private static int findMaxColumn(int[][] matr, int j,int k) {
        int a=Integer.MIN_VALUE;
        for (int i = 0; i<matr.length ; i++) {
            if (i!=j&&i!=k&&matr[i][j]>a){
                a=matr[i][j];
            }
        }
        return a;
    }

    private static int findMaxRow(int[][] matr, int i,int k) {
        int a=Integer.MIN_VALUE;
        for (int j = 0; j <matr.length ; j++) {
            if (i!=j&&j!=k&&matr[i][j]>a){
                a=matr[i][j];
            }
        }
        return a;
    }

    public static int [] searchMinRow(int [][] matr){
        int [] a=new int[matr.length];
        for (int i = 0; i <matr.length ; i++) {
            a[i]=matr[i][0];
            if(a[i]<0){
                a[i]-=1;
            }
            for (int j = 1; j <matr.length ; j++) {
                if(a[i]>matr[i][j] && matr[i][j]>0){
                    a[i]=matr[i][j];
                }
            }
        }
        return a;
    }
    public static int [] searchMaxRow(int [][] matr){
        int [] a=new int[matr.length];
        for (int i = 0; i <matr.length ; i++) {
            a[i]=matr[i][0];
            for (int j = 1; j <matr.length ; j++) {
                if(a[i]<matr[i][j]){
                    a[i]=matr[i][j];
                }
            }
        }
        return a;
    }

    public static int [] searchMinColumn(int [][] matr){
        int [] a=new int[matr.length];
        for (int i = 0; i <matr.length ; i++) {
            a[i]=matr[0][i];
            if(a[i]<0){
                a[i]-=1;
            }
            for (int j = 1; j <matr.length ; j++) {
                if(a[i]>matr[j][i] && matr[j][i]>0){
                    a[i]=matr[j][i];
                }
            }
        }
        return a;
    }
    public static int [] searchMaxColumn(int [][] matr){
        int [] a=new int[matr.length];
        for (int i = 0; i <matr.length ; i++) {
            a[i]=matr[0][i];
            for (int j = 1; j <matr.length ; j++) {
                if(a[i]<matr[j][i]){
                    a[i]=matr[j][i];
                }
            }
        }
        return a;
    }
    public static int [] searchMax(int [][] matr) {
        int a=0;
        int [] b={0,0};
        for (int i = 0; i <matr.length ; i++) {
            for (int j = 0; j <matr.length ; j++) {
                if(matr[i][j]>a){
                    a=matr[i][j];
                    b[0]=i;
                    b[1]=j;
                }
            }
        }

        return b;
    }

}
