/*
    ID:touchxy1
    LANG:C++
    PROG:dualpal
*/

#include <string.h>
#include <fstream>
#include <iostream>

using namespace std;
bool isPal(char* s)
{
	int n = strlen(s);
	bool isPal = true;
	for (int i = 0; i <= n/2; ++i)
	{
		if (s[i] != s[n - i - 1])
			isPal = false;
	}
	return isPal;
}

/*
n: number to convert
b: base
*/
void numConv(char* s, int n, int b)
{
	char table[] = {"0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"};
	char temp_s[32];
	int i = 0;
	while (n > 0)
	{
		temp_s[i++] = table[n % b];
		n /= b; 
	}
	temp_s[i] = '\0';

	/*Rever the order*/
	int len = i;
	for (int i = 0; i < len; ++i)
	{
		s[i] = temp_s[len - 1 - i];
	}
	s[len] = '\0';
}

int main(int argc, char const *argv[])
{
	ifstream fin("dualpal.in");
	ofstream fout("dualpal.out");

	int N;  //(1 <= N <= 15)  
	int S;  //(0 < S < 10000)

	fin >> N >> S;
	//cout << N << " " << S;

	int countPal = 0;  //countPal equals to N
	/*palindromic when written in two or more number bases*/
	/*int countDual = 0;*/

	char s[64]; 
	long results[N];
	for (long i = S + 1; ; ++i)
	{
		int countDual = 0;
		if (countPal == N) break;
		for (int base = 2; base <= 10; ++base)
		{
			numConv(s, i, base);
			if (isPal(s))
			{
				++countDual;
				if (countDual == 2)
				{
					results[countPal] = i;
					++countPal;
					//countDual = 0;  //Clean countDual
					break;
				}
			}
			//if (base == 10) countDual = 0;  //Clean countDual
		}
		
	}

	for (int i = 0; i < N; ++i)
		fout<< results[i] << std::endl;
	return 0;
}