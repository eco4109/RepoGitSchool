using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;


public class Ordenamiento_EREW
{

    public static void Main(string[] args)
    {
        int g;
        int[] L = { 0, 16, 22, 35, 40, 53, 66, 70, 85, 5, 28, 33, 55, 60, 69, 72, 78 };
        g = 16;
        Console.WriteLine("ODD-EVEN");
        Console.Write("Inicial:");
        Console.Write("\n");
        Console.Write("[");
        for (int i = 1; i <= g; i++)
        {
            Console.Write(L[i] + ",");
        }
        Console.Write("]");


        mergeSort(L, 1, g);
        Console.Write("\n");
        Console.WriteLine("Vector final:");
        Console.Write("[");
        for (int i = 1; i <= g; i++)
        {
            Console.Write(L[i] + ",");
        }
        Console.Write("]\n\n");
        Console.ReadKey();
    }

    public static void mergeSort(int[] L, int ini, int fin)
    {
        int centro = (fin + ini) / 2;
        if (ini < fin)
        {
            Parallel.Invoke(() => {
                mergeSort(L, ini, centro);
                mergeSort(L, centro + 1, fin);
                oddEvenMerge(L, ini, fin);
            });
        }
    }

    public static void interchange(int[] L, int a, int b)
    {
        int aux;
        aux = L[a];
        L[a] = L[b];
        L[b] = aux;
    }
    public static void oddEvenSplit(int[] L, int[] odd, int[] even, int ini, int fin)
    {
        int cont1 = 1, cont2 = 1;
        Parallel.For(0, 1, f => {
            for (int i = ini; i <= fin; i++)
            {
                if ((i % 2) == 0)
                {
                    even[cont1] = L[i];
                    cont1++;
                }
                else
                {
                    odd[cont2] = L[i];
                    cont2++;
                }
            }
        });
    }

    public static void oddEvenMerge(int[] L, int ini, int fin)
    {
        int n = fin - ini + 1;
        int[] odd = new int[16];
        int[] even = new int[16];
        if (n == 2)
        {
            if (L[ini] > L[fin])
            {
                interchange(L, ini, fin);
            }
        }
        else
        {

            oddEvenSplit(L, odd, even, ini, fin);
            Parallel.Invoke(() => {
                oddEvenMerge(odd, 1, (n / 2));
                oddEvenMerge(even, 1, (n / 2));
            });
            int aux2 = fin - n;

            Parallel.For(0, 1, f => {
                for (int i = 1; i <= (n / 2); i++)
                {

                    intercala(L, odd, even, i, aux2);
                }
            });


            for (int i = ini; i <= ((fin / 2) - 1); i++)
            {
                int a, b;
                a = 2 * i;
                b = (2 * i) + 1;
                if ((L[a]) > (L[b]))
                {
                    interchange(L, a, b);
                }
            }
        }
    }
    public static void intercala(int[] m, int[] odd, int[] even, int i, int aux)
    {
        m[(2 * i) - 1 + aux] = odd[i];
        m[(2 * i) + aux] = even[i];
    }

}