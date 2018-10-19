using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace AllSums_CREW
{
    class Program
    {
        public static int[] numbers;
        public static int n;
        public static CountdownEvent _countDown;

        static void Main(string[] args)
        {

            int i;
            numbers = new int[8];
            n = 8;

            Console.WriteLine("\n\tSumas CREW");
            for (i = 0; i < 8; i++)
            {
                Console.Write("\nEscribe el numero {0} de 8: ", i + 1);
                numbers[i] = int.Parse(Console.ReadLine());
            }

                for (i = 1; i <= Math.Log(n, 2); i++)
                {
                    _countDown = new CountdownEvent(n - (int)Math.Pow(2, i - 1));

                    Parallel.For((int)Math.Pow(2, i - 1) + 1, n + 1, j =>
                    {
                        int r = numbers[j - 1] + numbers[(j - (int)Math.Pow(2, i - 1)) - 1];
                        _countDown.Signal();

                        _countDown.Wait();
                        numbers[j - 1] = r;
                    });
                    print(numbers);
                }
            Console.WriteLine("La suma es: " + numbers[7]);
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
