using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

/*PROGRAMACION PARALELA Y DISTRIBUIDA
 * 2016-A
 * ORDENAMIENTO CRCW PROGRAMACION PARALELA USANDO PARALLEL.FOR Y PROGRAMACIÓN FUNCIONAL
 * 
 * DAVID HERNANDEZ HERRERA
 * LUIS ANGEL ROMERO ESCALONA
 * ERICK ALVAREZ PORCAYO
*/

namespace ordenCRCW
{
	class MainClass
	{
		int[] L = { 95, 10, 6, 15 };
		int[] T = { 0, 0, 0, 0 };
		int[] win = { 0, 0, 0, 0 };
		int n = 4;
		int i, j;

		public void sortCRCW()
		{
			imprimir();
			for (i = 1; i < n; i++)
			{
				Parallel.For (0, 1, h =>{
					win[i] = 0;
				});
			}

			for (i = 0; i < n; i++)
			{
				for (j = 1; j < n; j++)
				{
					if (i < j)
					{
						Parallel.For (0, 1, l =>{
							if (L[i] > L[j])
							{
								win[i] = win[i] + 1;
							}
							else
							{
								win[j] = win[j] + 1;
							}

						});					}
				}
			}

			imprimir3();
			for (i = 0; i < n; i++)
			{
				Parallel.For (0, 1, t =>{
					T[win[i]] = L[i];
				});
			}
			Console.Write("\n");
			imprimir2();
			Console.ReadKey(true);
		}
			
		public void imprimir()
		{
			Console.Write("\n");
			Console.Write("   El vector original es: [");
			foreach (int z in L)
			{
				Console.Write("{0} ", z);
			}
			Console.Write("]\n");
		}

		public void imprimir2()
		{
			Console.Write("\n");
			Console.Write("   El vector ordenado es: [");
			foreach (int z in T)
			{
				Console.Write("{0} ", z);
			}
			Console.Write("]\n");
		}

		public void imprimir3()
		{
			Console.Write("\n");
			Console.Write("   El vector win es: [");
			foreach (int z in win)
			{
				Console.Write("{0} ", z);
			}
			Console.Write("]\n");
		}

		public static void Main (string[] args)
		{
			Console.WriteLine("\t\tORDENAMIENTO CRCW");
			MainClass p = new MainClass();

			p.sortCRCW();
		}
	}
}
