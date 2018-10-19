using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Threading;

/*PROGRAMACION PARALELA Y DISTRIBUIDA
 * 2016-A
 * BUSQUEDA EREW PROGRAMACION PARALELA USANDO PARALLEL.FOR Y PROGRAMACIÓN FUNCIONAL
 * 
 * DAVID HERNANDEZ HERRERA
 * LUIS ANGEL ROMERO ESCALONA
 * ERICK ALVAREZ PORCAYO
*/

namespace busquedaEREW
{
	class MainClass
	{
		int[] L = { 0, 2, -1, 23, -4, 2, 5, -2, 0, 5, 1, 5, -5, 8, 5, 3, -2 };
		int[] A = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int i, j, lg;
		int n = 17;
		int x = 5;

		public void searchEREW()
		{
			Console.WriteLine("\n");
			Console.WriteLine("El numero que se buscara es: " + x);
			Console.WriteLine("\n");

			lg = (int)(Math.Log(n, 2));

			broadCast();

			for (i = 1; i < n; i++)
			{
				Parallel.For(0, 1, y  =>{
					if (L[i] == A[i]){
						A[i] = i;
					}
					else{
						A[i] = 100;
					}
				});
			}
			min();

			Console.WriteLine("El numero buscado esta en la posicion: " + A[1]);
			Console.ReadKey(true);
		}

		public void broadCast(){
			A[1] = x;
			int aux, aux2;
			for (i = 1; i < lg + 1; i++)
			{
				aux = (int)(Math.Pow(2, i - 1)) + 1;
				aux2 = (int)(Math.Pow(2, i)) + 1;
				for (j = aux; j < aux2; j++)
				{
					Parallel.For(0, 16, z  =>{
						A[j] = A[j - (int)(Math.Pow(2, (i - 1)))];
					});

				}
			}
		}

		public void min()
		{
			i = 1;
			int aux = ((int)(n / (Math.Pow(2, i))) + 1);
			for (i = 1; i < n; i++)
			{
				for (j = 1; j < aux; j++)
				{
					Parallel.For(1, 16, w  =>{
						if (A[j * 2] > A[(j * 2) - 1]){
							A[j] = A[(j * 2) - 1];
						}
						else{
							A[j] = A[j * 2];
						}
					});
				}
			}
		}

		public void imprimir()
		{
			Console.WriteLine("\n");
			Console.Write("L = [");
			foreach (int z in L)
			{
				Console.Write("{0} ", z);
			}
			Console.Write("]\n");
		}

		public static void Main (string[] args)
		{
			Console.WriteLine("\t\tBUSQUEDA EREW");
			MainClass p = new MainClass();
			p.imprimir();
			p.searchEREW();
		}
	}
}
