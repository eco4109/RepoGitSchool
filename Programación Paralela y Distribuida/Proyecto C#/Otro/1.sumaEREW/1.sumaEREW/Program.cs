using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ErewSuma
{
    class Program
    {
        public static int[] numbers;
        public static int n;
        public static bool ban = true;
        public static void Main(string[] args)
        {
            int i;
            numbers = new int[8];
            n = 8;

            Console.WriteLine("\n\tSuma EREW");
            for (i = 0; i < 8; i++)
            {
                Console.Write("\nEscribe el numero {0} de 8: ", i+1);
                numbers[i] = int.Parse(Console.ReadLine());
            }
               for (i = 1; i <= Math.Log(numbers.Length, 2); i++)
                {
                    Parallel.For(1, (n / 2) + 1, j =>
                    {

                        if (((2 * j) % (Math.Pow(2, i))) == 0)
                        {
                            numbers[(2 * j) - 1] = numbers[(2 * j) - 1] + numbers[((2 * j) - (int)Math.Pow(2, i - 1)) - 1];
                        }

                    });
                    print();
                }
            Console.WriteLine("La suma es: " + numbers[numbers.Length - 1]);
            Console.ReadKey();
        }

        public static void print()
        {
            for (int i = 0; i < numbers.Length; i++)
            {
                Console.Write(numbers[i]);
                if (i != (numbers.Length - 1))
                    Console.Write(",");
            }
            Console.WriteLine();
        }

    }
}
