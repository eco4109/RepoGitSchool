using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

/*PROGRAMACION PARALELA Y DISTRIBUIDA
 * 2016-A
 * SUMA CREW PROGRAMACION PARALELA USANDO PARALLEL.FOR Y PROGRAMACIÓN FUNCIONAL
 * 
 * DAVID HERNANDEZ HERRERA
 * LUIS ANGEL ROMERO ESCALONA
 * ERICK ALVAREZ PORCAYO
*/

namespace sumaCREW
{
	class MainClass
	{
		int[] vec = { 0, 5, 2, 10, 1, 8, 12, 7, 3 };
		int n = 8;
		int i, j, x;

		public void Suma()
		{
			x = (int)(Math.Log(n, 2));
			//Console.Write("\n" + x);
			imprimir();
			for (i = 1; i <= x; i++)
			{
				for (j = 1; j <= n; j++)
				{
					Parallel.For (0, 1, f =>{
						if ((Math.Pow(2, i - 1)) == 1)
						{
							vec[j] = vec[j] + vec[j - (int)(Math.Pow(2, i - 1))];
						}
					});
				}
			}
			imprimir();
			Console.Write("\n");
			Console.WriteLine("La suma es:" + vec[n]);
			Console.ReadKey(true);
		}

		public void imprimir()
		{
			Console.Write("[");
			foreach (int z in vec)
			{
				Console.Write("{0} ", z);
			}
			Console.Write("]\n");
		}

		public static void Main (string[] args)
		{
			Console.WriteLine("\t\tSUMA CREW");
			MainClass p = new MainClass();
			p.Suma();
		}
	}
}
