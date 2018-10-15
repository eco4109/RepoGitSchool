using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
namespace MultiMatiz
{
    class Program
    {
        public static int n;
        public static int[,] A, B;
        public static int[,,] C;
        public static void Main(string[] args)
        {
            Console.WriteLine("\n\tMultiplicacion de matrices (2x2)");

            n = 2;

            A = new int[n + 1, n + 1];
            B = new int[n + 1, n + 1];
            C = new int[n + 1, n + 1, n + 1];

            Console.WriteLine("Ingresa la Matriz A :");
            PideMatriz(ref A, ref n);
            Console.WriteLine("Ingresa la Matriz B :");
            PideMatriz(ref B, ref n);

            Parallel.For(1, n + 1, i =>
            {
                Parallel.For(1, n + 1, j =>
                {
                    Parallel.For(1, n + 1, k =>
                    {
                        C[i, j, k] = A[i, k] * B[k, j];
                    });
                });
            });

            for (int l = 1; l <= Math.Log(n, 2); l++)
            {
                Parallel.For(1, n + 1, i =>
                {
                    Parallel.For(1, n + 1, j =>
                    {
                        Parallel.For(1, (n / 2) + 1, k =>
                        {
                            if (((2 * k) % (int)Math.Pow(2, l)) == 0)
                            {

                                int r = C[i, j, 2 * k] + C[i, j, (2 * k) - (int)Math.Pow(2, l - 1)];
                                
                                C[i, j, 2 * k] = C[i, j, 2 * k] + C[i, j, (2 * k) - (int)Math.Pow(2, l - 1)];
                            }
                        });
                    });
                });
            }

            resultado();

            Console.ReadKey();
        }

        public static void PideMatriz(ref int[,] M, ref int n)
        {
            for (int i = 1; i <= n; i++)
            {
                for (int j = 1; j <= n; j++)
                {
                    while (true)
                    {
                        Console.Write("Dame el elemento " + i + "," + j + " de la matriz :");

                        if (int.TryParse(Console.ReadLine(), out M[i, j]))
                            break;
                        else
                            Console.WriteLine("Ingresa un valor numerico!");
                    }
                }
            }
        }

        public static void resultado()
        {
            Console.WriteLine("Matriz resultante : \n");
            for (int i = 1; i <= n; i++)
            {
                for (int j = 1; j <= n; j++)
                {
                    Console.Write(C[i, j, n] + " ");
                }
                Console.WriteLine();
            }
        }
    }
}