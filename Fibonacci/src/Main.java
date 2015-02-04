import java.math.BigInteger;
import java.util.Iterator;
import java.util.LinkedList;


public class Main {
	
	public static BigInteger fibonacci(int n){
		BigInteger x = new BigInteger("0");
		BigInteger y = new BigInteger("1");
		BigInteger z = new BigInteger("1");
		for (int i = 0; i<n ; i++) {
			x = y;
			y = z;
			z = x.add(y);
		}
		return x;
	}
	
	
	public static void power(BigInteger[][] a, int n){
		BigInteger matrix[][] = new BigInteger[2][2];
		matrix[0][0] = new BigInteger("0");
		matrix[0][1] = new BigInteger("1");
		matrix[1][0] = new BigInteger("1");
		matrix[1][1] = new BigInteger("1");
		for (int i = 1; i < n; i++){
			multiply(a, matrix);
		}
	}
	
	public static void multiply(BigInteger F[][], BigInteger M[][]){
		BigInteger x = (F[0][0].multiply(M[0][0])).add(F[0][1].multiply(M[1][0]));
		BigInteger y = (F[0][0].multiply(M[0][1])).add(F[0][1].multiply(M[1][1]));
		BigInteger z = (F[1][0].multiply(M[0][0])).add(F[1][1].multiply(M[1][0]));
		BigInteger w = (F[1][0].multiply(M[0][1])).add(F[1][1].multiply(M[1][1]));

		F[0][0] = x;
		F[0][1] = y;
		F[1][0] = z;
		F[1][1] = w;
	}
	
	public static BigInteger fibonacciMatrix(int n){
		BigInteger identity[][] = new BigInteger[2][2];
		identity[0][0] = new BigInteger("0");
		identity[0][1] = new BigInteger("1");
		identity[1][0] = new BigInteger("1");
		identity[1][1] = new BigInteger("1");
		power(identity, n-1);
		return identity[1][1];
	}
	
	public static LinkedList<Integer> simplify(int n){
		String binaryString = Integer.toBinaryString(n);
		char[] binary = binaryString.toCharArray();
		LinkedList<Integer> index = new LinkedList<Integer>();
 		for (int i = 0, j = binary.length-1; i < binary.length; i++,j--) {
			if (binary[i] == '1'){
				int exp = (int)Math.pow(2, j);
				index.add(exp);
			}
		}
		return index;
	}
	
	public static BigInteger[][][] /*LinkedList<LinkedList<BigInteger>>*/ getMatrices(LinkedList<Integer> lista){
		/*LinkedList<LinkedList<BigInteger>> matrix = new LinkedList<LinkedList<BigInteger>>();
		matrix.add(new LinkedList<BigInteger>());
		matrix.add(new LinkedList<BigInteger>());
		matrix.add(new LinkedList<BigInteger>());*/
		
		BigInteger[][][] matrices = new BigInteger[2][2][lista.size()];
		BigInteger identity[][] = new BigInteger[2][2];
		identity[0][0] = new BigInteger("0");
		identity[0][1] = new BigInteger("1");
		identity[1][0] = new BigInteger("1");
		identity[1][1] = new BigInteger("1");
		Iterator iterator = lista.listIterator();
		int k =0;
		while(iterator.hasNext()){
			int nextIndex = (int)iterator.next();
			System.out.println("nextIndex: " + nextIndex);
			power(identity, nextIndex);
			
			for (int i = 0; i < identity.length; i++) {
				for (int j = 0; j < identity.length; j++) {
					matrices[i][j][k] = identity[i][j];
					//matrix.get(k).add(identity[i][j]);
				}
			}
			identity[0][0] = new BigInteger("0");
			identity[0][1] = new BigInteger("1");
			identity[1][0] = new BigInteger("1");
			identity[1][1] = new BigInteger("1");
			System.out.println("k: " + k);
			k++;
		}
		//return matrix;
		return matrices;
	}
	
	public static void finalMultiplication(BigInteger[][][] matrices, int size){
		System.out.println("teste");
		
		for (int k =0; k < size -1; k++){
				
		}
		
		System.out.println("LOL");
		
		
		for (int i = 0; i < size-1; i++) {
			//multiplyInside(matrices[0],matrices[1]);
			//System.out.println(matrices[i][i][i]);
		}
	}
	
	public static BigInteger[][] multiplyInside(BigInteger[][][] m, int size){
		BigInteger[][] result = new BigInteger[2][2];
		
		for (int k = 0; k < size; k++){
			BigInteger matrix[][] = new BigInteger[2][2];
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix.length; j++) {
					matrix[i][j] = m[i][j][k];
				}
			}
		}
		
		
		/*BigInteger x = (m[0][0][0].multiply(m[0][0][0])).add(m[0][1][0].multiply(m[1][0][0]));
		BigInteger y = (F[0][0].multiply(M[0][1])).add(F[0][1].multiply(M[1][1]));
		BigInteger z = (F[1][0].multiply(M[0][0])).add(F[1][1].multiply(M[1][0]));
		BigInteger w = (F[1][0].multiply(M[0][1])).add(F[1][1].multiply(M[1][1]));

		F[0][0] = x;
		F[0][1] = y;
		F[1][0] = z;
		F[1][1] = w;*/
		
		
		
		return result;
	}
	
	public static BigInteger Fibonacci(int n) {
        if (n == 0) {
            return new BigInteger("0");
        }
        BigInteger base[][] = new BigInteger[2][2];
		base[0][0] = new BigInteger("0");
		base[0][1] = new BigInteger("1");
		base[1][0] = new BigInteger("1");
		base[1][1] = new BigInteger("1");
		BigInteger result[][] = new BigInteger[2][2];
		result[0][0] = new BigInteger("0");
		result[0][1] = new BigInteger("1");
		result[1][0] = new BigInteger("1");
		result[1][1] = new BigInteger("1");
		

        while (n > 0) {
            if (n%2 == 1){ 
            	//result = MultiplyMatrix(result, base);
            	multiply(result,base);
            }
            //base = MultiplyMatrix(base, base);
            multiply(base,base);
            n/= 2;
        }
        return result[0][0];
    }

    public static int[][] MultiplyMatrix(int[][] mat1, int[][] mat2) {
        return new int[][] {
                { mat1[0][0]*mat2[0][0] + mat1[0][1]*mat2[1][0], mat1[0][0]*mat2[0][1] + mat1[0][1]*mat2[1][1] },
                { mat1[1][0]*mat2[0][0] + mat1[1][1]*mat2[1][0], mat1[1][0]*mat2[0][1] + mat1[1][1]*mat2[1][1] }
        };
    }
	
	
	public static void main(String args[]){
		LinkedList<Integer> index = simplify(100);
		BigInteger[][][] m = getMatrices(index);
		BigInteger[][] result = multiplyInside(m, index.size());
		
		//LinkedList<LinkedList<BigInteger>> m = getMatrices(index);
		
		/*for (int k = 0; k < index.size(); k++) {
			BigInteger matrix[][] = new BigInteger[2][2];
			for (int i = 0; i < m.size(); i++) {
				for (int j = 0; j <= m.size(); j++) {
					//System.out.println(m.get(i).get(j));//  m[i][j][k]);
					matrix[i][j] = m.get(i).get(j);
				}
				BigInteger matrix2[][] = new BigInteger[2][2];

			}
			
		}*/
		
		/*
		//for (int k = 0; k < index.size(); k++){
			for (int i = 0; i < m.size(); i++) {
				for (int j = 0; j <= m.size(); j++) {
					System.out.println(m.get(i).get(j));//  m[i][j][k]);
				}
			}*/
		//}
		
		//finalMultiplication(m, index.size());
		
		System.out.println("huehaue");
		
		
		long t1 = System.currentTimeMillis();
		System.out.println(fibonacci(100000));
		long t2 = System.currentTimeMillis();
		System.out.println();
		long t3 = System.currentTimeMillis();
		System.out.println(Fibonacci(100000));
		long t4 = System.currentTimeMillis();
		/*for (int i = 0; i < 300; i++) {
			fibonacci(i);
			//System.out.println(fibonacci(i));
		}*/
		
		System.out.println("tempo gasto alg1: " + (t2-t1));/*
		long t3 = System.currentTimeMillis();
		for (int i = 0; i < 5; i++) {
			fibonacciMatrix(i);
			//System.out.println(fibonacciMatrix(i));
		}
		long t4 = System.currentTimeMillis();*/
		System.out.println("tempo gasto alg2: " + (t4-t3));/*
		BigInteger identity[][] = new BigInteger[2][2];
		identity[0][0] = new BigInteger("0");
		identity[0][1] = new BigInteger("1");
		identity[1][0] = new BigInteger("1");
		identity[1][1] = new BigInteger("1");
		//long t1 = System.nanoTime();
		power(identity, 100);
		//long t2 = System.nanoTime();
		System.out.println("power100 - " + (t2-t1)/1000);


		BigInteger originalMatrix[][] = new BigInteger[2][2];
		originalMatrix[0][0] = identity[0][0];
		originalMatrix[0][1] = identity[0][1];
		originalMatrix[1][0] = identity[1][0];
		originalMatrix[1][1] = identity[1][1];

		
		
		
		
		System.out.println();
		
		//BigInteger x100 = identity[1][1];
		identity[0][0] = new BigInteger("0");
		identity[0][1] = new BigInteger("1");
		identity[1][0] = new BigInteger("1");
		identity[1][1] = new BigInteger("1");
		long t3 = System.nanoTime();
		power(identity, 64);
		long t4 = System.nanoTime();
		long x1 = t4-t3;
		
		BigInteger matrix64[][] = new BigInteger[2][2];
		matrix64[0][0] = identity[0][0];
		matrix64[0][1] = identity[0][1];
		matrix64[1][0] = identity[1][0];
		matrix64[1][1] = identity[1][1];
		
		//matrix64 = identity;
		
		
		
		System.out.println();
		
		//BigInteger x64 = identity[1][1];
		
		
		identity[0][0] = new BigInteger("0");
		identity[0][1] = new BigInteger("1");
		identity[1][0] = new BigInteger("1");
		identity[1][1] = new BigInteger("1");
		
		long t5 = System.nanoTime();
		power(identity, 32);
		long t6 = System.nanoTime();
		long x2 = t6-t5;
		//power(identity, 32);
		
		BigInteger matrix32[][] = new BigInteger[2][2];
		matrix32[0][0] = identity[0][0];
		matrix32[0][1] = identity[0][1];
		matrix32[1][0] = identity[1][0];
		matrix32[1][1] = identity[1][1];
		
		
		//BigInteger x32 = identity[1][1];
		identity[0][0] = new BigInteger("0");
		identity[0][1] = new BigInteger("1");
		identity[1][0] = new BigInteger("1");
		identity[1][1] = new BigInteger("1");
		long t7 = System.nanoTime();
		power(identity, 4);
		long t8 = System.nanoTime();
		long x3 = t8-t7;
		//power(identity, 4);
		
		System.out.println("power matrix: " + (x1+x2+x3)/1000);
		
		//BigInteger x4 = identity[1][1];
		//System.out.println("x100 " + x100);
		BigInteger matrix4[][] = new BigInteger[2][2];
		matrix4[0][0] = identity[0][0];
		matrix4[0][1] = identity[0][1];
		matrix4[1][0] = identity[1][0];
		matrix4[1][1] = identity[1][1];
		
		System.out.println("x100: ");
		for (int i = 0; i < identity.length; i++) {
			for (int j = 0; j < identity.length; j++) {
				System.out.println(originalMatrix[i][j]);
			}
		}
		System.out.println();
		System.out.println("x64");
		for (int i = 0; i < identity.length; i++) {
			for (int j = 0; j < identity.length; j++) {
				System.out.println(matrix64[i][j]);
			}
		}
		System.out.println();
		System.out.println("x32");
		for (int i = 0; i < identity.length; i++) {
			for (int j = 0; j < identity.length; j++) {
				System.out.println(matrix32[i][j]);
			}
		}
		System.out.println();
		System.out.println("x4");
		for (int i = 0; i < identity.length; i++) {
			for (int j = 0; j < identity.length; j++) {
				System.out.println(matrix4[i][j]);
			}
		}
		System.out.println("x64*x32*x4");
		multiply(matrix64, matrix32);
		multiply(matrix64, matrix4);
		for (int i = 0; i < identity.length; i++) {
			for (int j = 0; j < identity.length; j++) {
				System.out.println(matrix64[i][j]);
			}
		}
		/*
		System.out.println("x64: " + x64);
		System.out.println("x32: " + x32);
		System.out.println("x4: " + x4);
		System.out.println("x64*x32*x4 " + ((x64.multiply(x32)).multiply(x4)) );
		System.out.println("x64+x32+x4 " + (x64.add(x32).add(x4)));*/

		/*
		for (int i = 0; i < identity.length; i++) {
			for (int j = 0; j < identity.length; j++) {
				System.out.println(identity[i][j]);
			}
		}*/
	}
}
