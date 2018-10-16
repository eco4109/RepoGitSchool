using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace Ordenamiento_PRAM_CRCW
{
    class Program
    {
        public static int[] numbers, win;
        public static int n;
        public static CountdownEvent _countDown;

        static void Main(string[] args)
        {
            n = 8;
            numbers = new int[n];

            Console.WriteLine("\n  ordenamiento CRCW");

            for (int i = 0; i < 8; i++)
            {
                Console.Write("\nEscribe el numero {0} de 8: ", i + 1);
                numbers[i] = int.Parse(Console.ReadLine());
            }

            win = new int[n];

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
                                win[i] = win[i] + 1;
                            else
                                win[j] = win[j] + 1;
                        }
                    });
                });


                _countDown = new CountdownEvent(n);
                Parallel.For(0, n, i =>
                {
                    int r = numbers[i];
                    _countDown.Signal();

                    _countDown.Wait();
                    numbers[win[i]] = r;
                });

                Console.Write("Win: ");
                print(win);
                Console.Write("L: ");
                print(numbers);

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
