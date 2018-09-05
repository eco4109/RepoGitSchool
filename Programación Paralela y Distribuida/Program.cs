using System;
using System.Diagnostics;
using System.Threading.Tasks;
//programa de suma EREW 1
namespace ConsoleApp1{
    class Program
    {
        int[] vec = { 0, 5, 2, 10, 1, 8, 12, 7, 3 };
        int n = 8;
        int i, j = 1, x;

        public void Suma()
        {
            x = (int)(Math.Log(n, 2));
            imprimir();
            for (i = 1; i < x + 1; i++)
            {
                for (j = 1; j <= (n / 2); j++)
                {
                    Parallel.For(0, 1, index => {
                        if (((2 * j) % ((int)(Math.Pow(2, i)))) == 0)
                        {
                            vec[j * 2] = vec[j * 2] + vec[(j * 2) - (int)(Math.Pow(2, i - 1))];
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

        public static void Main(string[] args)
        {
            Console.WriteLine("\t\tSUMA EREW");
            Program p = new Program();
            p.Suma();
        }
    }
}
