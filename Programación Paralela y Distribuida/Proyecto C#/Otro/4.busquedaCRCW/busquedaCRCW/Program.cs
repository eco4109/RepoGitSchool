using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace Busqueda_PRAM_CRCW
{
    class Program
    {
        public static String numeros;
        public static String[] num;
        public static int[] numbers, win;
        public static bool ban = true;
        public static int n, j, indexMin;

        static void Main(string[] args)
        {

            n = 8;
            numbers = new int[n];

            Console.WriteLine("\n  Busqueda CRCW");

            for (int i = 0; i < 8; i++)
            {
                Console.Write("\nEscribe el numero {0} de 8: ", i + 1);
                numbers[i] = int.Parse(Console.ReadLine());
            }

            win = new int[n];
            indexMin = 0;

                Parallel.For(0, n, i =>
                {
                    win[i] = 0;
                });

                Parallel.For(0, n, i =>
                {
                    Parallel.For(0, n, j =>
                    {
                        if (i < j)
                        {
                            if (numbers[i] > numbers[j])
                                win[i] = 1;
                            else
                                win[j] = 1;
                        }
                    });
                });

                Console.Write("Win: ");
                print(win);

                Parallel.For(0, n, i =>
                {
                    if (win[i] == 0)
                        indexMin = i;
                });
                Console.WriteLine("Elemento minimo : " + numbers[indexMin]);
           
            Console.ReadKey();
        }

        public static void print(int[] L)
        {
            for (int i = 0; i < L.Length; i++)
            {
                Console.Write(L[i]);
                if (i != (L.Length - 1))
                    Console.Write(",");
            }
            Console.WriteLine();
        }
    }
}
