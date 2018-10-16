using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Busqueda_PRAM_EREW
{
    class Program
    {
        public static int n, buscar;
        public static bool ban = true;
        public static int[] A;
        public static int[] L;

        static void Main(string[] args)
        {
            n = 8;
            L = new int[n];

            Console.WriteLine("\n  Busqueda EREW");
            
            for (int i = 0; i < 8; i++)
            {
                Console.Write("\nEscribe el numero {0} de 8: ", i + 1);
                L[i] = int.Parse(Console.ReadLine());
            }
            A = new int[n];

            Console.Write("\nNumero a buscar : ");
            int.TryParse(Console.ReadLine(), out buscar);

            BroadCast(buscar);

            Parallel.For(1, n + 1, i =>
            {
                if (L[i - 1] == A[i - 1])
                    A[i - 1] = i;
                else
                    A[i - 1] = 99999;

                Console.Write("Vector L: ");
                print(L);
                Console.Write("Vector Temp: ");
                print(A);
            });
            Console.WriteLine("Posicion : " + MinPRAM(A));
            Console.ReadKey();
        }

        public static int MinPRAM(int[] L)
        {
            for (int j = 1; j <= Math.Log(L.Length, 2); j++)
            {
                Parallel.For(1, (L.Length / (int)Math.Pow(2, j)) + 1, i =>
                {
                    if (L[((2 * i) - 1) - 1] > L[(2 * i) - 1])
                        L[i - 1] = L[(2 * i) - 1];
                    else
                        L[i - 1] = L[((2 * i) - 1) - 1];
                });
            }

            Console.Write("Temp Final: ");
            print(L);

            return L[0];
        }

        public static void BroadCast(int x)
        {
            A[0] = x;
            for (int i = 1; i <= Math.Log(A.Length, 2); i++)
            {
                Parallel.For((int)Math.Pow(2, i - 1) + 1, (int)Math.Pow(2, i) + 1, j =>
                {
                    A[j - 1] = A[(j - (int)Math.Pow(2, i - 1)) - 1];
                });
            }
        }

        public static void print(int[] pr)
        {
            for (int i = 0; i < pr.Length; i++)
            {
                Console.Write(pr[i]);
                if (i != (pr.Length - 1))
                    Console.Write(",");
            }
            Console.WriteLine();
        }

    }
}