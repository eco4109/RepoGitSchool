using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

/*PROGRAMACION PARALELA Y DISTRIBUIDA
 * 
 * SUMA EREW PROGRAMACION PARALELA USANDO PARALLEL.FOR Y PROGRAMACIÓN FUNCIONAL
 * 
 
*/

namespace sumaEREW
{
	class MainClass
	{
		int[] vec = { 0, 5, 2, 10, 1, 8, 12, 7, 3 };
		int n = 8;
		int i, j = 1, x;

		public void Suma(){
			x = (int)(Math.Log(n, 2));
			imprimir();
			for (i = 1; i < x + 1; i++)
			{
				for (j = 1; j <= (n / 2); j++) {
					Parallel.For (0, 1, index => {
						if (((2 * j) % ((int)(Math.Pow (2, i)))) == 0) {
							vec [j * 2] = vec [j * 2] + vec [(j * 2) - (int)(Math.Pow (2, i - 1))];
						}
					});
				}
			}
			imprimir();
			Console.Write("\n");
			Console.WriteLine("La suma es:" + vec[n]);
			Console.ReadKey(true);
		}

		public void EREW()
		{
			if (((2 * j) % ((int)(Math.Pow(2, i)))) == 0)
			{
				vec[j * 2] = vec[j * 2] + vec[(j * 2) - (int)(Math.Pow(2, i - 1))];
			}
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
			Console.WriteLine("\t\tSUMA EREW");
			MainClass p = new MainClass();
			p.Suma();
		}
	}
}
