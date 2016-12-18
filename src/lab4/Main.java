package lab4;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by 111 on 15.12.2016.
 */
public class Main {
    static int count=2;
    public static void main(String[] args) {
        double [][] matrix={{Double.NaN, 1, 6, 7},
                          {4,Double.NaN,1,2},
                           {3,3,Double.NaN,4},
                             {1,3,1,Double.NaN}    };
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j <matrix[i].length; j++) {
                matrix[i][j]=1./matrix[i][j];
            }
//            System.out.println(Arrays.toString(matrix[i]));
        }
//        System.out.println();
        String msg="The longest way:\n";
        msg=searchWay(matrix,msg);
        System.out.println(msg);
    }

    public static String  searchWay(double [][] matr, String msg) {
        if (count < matr.length) {
            count++;
            double[][] bufMatr = new double[matr.length][matr.length];
            for (int i = 0; i < matr.length; i++) {
                bufMatr[i] = Arrays.copyOf(matr[i], matr.length);
            }
            double[] minVal = searchMinRow(bufMatr);
            for (int i = 0; i < matr.length; i++) {
                for (int j = 0; j < matr.length; j++) {
                    if (bufMatr[i][j] != Integer.MIN_VALUE) {
                        bufMatr[i][j] -= minVal[i];
                    }
                }
            }
//        System.out.println("\nMin:");
//        for (int i = 0; i < bufMatr.length; i++) {
//            System.out.println(Arrays.toString(bufMatr[i]));
//        }
            minVal = searchMinColumn(bufMatr);
            for (int i = 0; i < matr.length; i++) {
                for (int j = 0; j < matr.length; j++) {
                    if (bufMatr[j][i] != Integer.MIN_VALUE) {
                        bufMatr[j][i] -= minVal[i];
                    }
                }
            }
//        System.out.println("\nMax:");
//        for (int i = 0; i < bufMatr.length; i++) {
//            System.out.println(Arrays.toString(bufMatr[i]));
//        }
            int[] partOfWay = findCurrrenElement(bufMatr);
//            System.out.println(Arrays.toString(partOfWay));

            msg += partOfWay[0] + "->" + partOfWay[1] + ";\n";
            for (int i = 0; i < matr.length; i++) {
                matr[i][partOfWay[1]] = Double.NaN;
                matr[partOfWay[0]][i] = Double.NaN;
            }
            matr[partOfWay[1]][partOfWay[0]]=Double.NaN;
           /* for (int i = 0; i < bufMatr.length; i++) {
                System.out.println(Arrays.toString(matr[i]));
            }*/
            //System.out.println(partOfWay[0]+"->"+partOfWay[1]);
            msg=searchWay(matr, msg);



        }
        else {
            int [] partOfWay=searchMin(matr);
           /* for (int i = 0; i < matr.length; i++) {
                System.out.println(Arrays.toString(matr[i]));
            }*/
            //System.out.println(partOfWay[0]+"->"+partOfWay[1]);
            msg+=partOfWay[0]+"->"+partOfWay[1];
        }
        return msg;
    }

    private static int[] searchMin(double[][] matr) {
        double a=Double.MAX_VALUE;
        int [] b={0,0};
        for (int i = 0; i <matr.length ; i++) {
            for (int j = 0; j <matr.length ; j++) {
                if(matr[i][j]<a){
                    a=matr[i][j];
                    b[0]=i;
                    b[1]=j;
                }
            }
        }
        return b;
    }

    private static int[] findCurrrenElement(double[][] matr) {
        int[]a={0,0};
        double b=Integer.MIN_VALUE;
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

    private static double findDifference(double[][] matr, int i, int j) {
        double a=findMaxRow(matr,i,j)+findMaxColumn(matr,j,i);

        return a;
    }

    private static double findMaxColumn(double[][] matr, int j, int k) {
        double a=Double.MIN_VALUE;
        for (int i = 0; i<matr.length ; i++) {
            if (i!=j&&i!=k&&matr[i][j]>a){
                a=matr[i][j];
            }
        }
        return a;
    }

    private static double findMaxRow(double[][] matr, int i, int k) {
        double a=Double.MIN_VALUE;
        for (int j = 0; j <matr.length ; j++) {
            if (i!=j&&j!=k&&matr[i][j]>a){
                a=matr[i][j];
            }
        }
        return a;
    }

    public static double [] searchMinRow(double[][] matr){
        double [] a=new double[matr.length];
        for (int i = 0; i <matr.length ; i++) {
            a[i]=Double.MAX_VALUE;
            for (int j = 0; j <matr.length ; j++) {
                if(a[i]>matr[i][j]){
                    a[i]=matr[i][j];
                }
            }
        }
        return a;
    }

    public static double [] searchMinColumn(double[][] matr){
        double [] a=new double[matr.length];
        for (int i = 0; i <matr.length ; i++) {
            a[i]=Double.MAX_VALUE;
            for (int j = 0; j <matr.length ; j++) {
                if(a[i]>matr[j][i]){
                    a[i]=matr[j][i];
                }
            }
        }
        return a;
    }

}
