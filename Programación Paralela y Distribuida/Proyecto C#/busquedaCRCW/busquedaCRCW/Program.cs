using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

/*PROGRAMACION PARALELA Y DISTRIBUIDA
 * 2016-A
 * BUSQUEDA CRCW PROGRAMACION PARALELA USANDO PARALLEL.FOR Y PROGRAMACIÓN FUNCIONAL
 * 
 * DAVID HERNANDEZ HERRERA
 * LUIS ANGEL ROMERO ESCALONA
 * ERICK ALVAREZ PORCAYO
*/

namespace busquedaCRCW
{
	class MainClass
	{
		int[] L = { 95, 10, 6, 15 };
		int[] win = { 1, 1, 0, 1 };
		int n = 3;
		int i, j, result;

		public void Ordenar()
		{
			imprimir();
			for (i = 1; i <= n; i++)
			{
				Parallel.For(0, 1, a=>{
					win[i] = 0;
				});
			}

			for (i = 0; i <= n; i++)
			{
				for (j = 2; j <= n; j++)
				{
					if (i < j)
					{
						Parallel.For(0, 1, d =>{
							if (L[i] > L[j])
							{
								win[i] = 1;
							}
							else
							{
								win[j] = 1;
							}
						});
					}
					imprimir2();
				}
			}

			for (i = 1; i <= n; i++)
			{
				for (j = 1; j <= n; j++)
				{
					Parallel.For(0, 1, m =>{
						if (win[i] == 0)
						{
							result = i;
						}
					});
				}
			}
			Console.Write("\n");
			Console.WriteLine("El valor menor es: " + L[result]);
			Console.ReadKey(true);
		}

		public void imprimir()
		{
			Console.Write("L=[");
			foreach (int z in L)
			{
				Console.Write("{0} ", z);
			}
			Console.Write("]\n\n");
		}

		public void imprimir2()
		{
			Console.Write("win=[");
			foreach (int z in win)
			{
				Console.Write("{0} ", z);
			}
			Console.Write("]\n\n");
		}

		public static void Main (string[] args)
		{
			Console.WriteLine("\t\tBUSQUEDA CRCW");
			MainClass p = new MainClass();
			p.Ordenar();
		}
	}
}
