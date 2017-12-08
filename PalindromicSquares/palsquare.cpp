#include <string.h>
#include <stdio.h>
#include <fstream> 

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
	/*FILE* fin = fopen("palsquare.in","r");
	FILE* fout = fopen("palsquare.out","w");*/
	ifstream fin("palsquare.in");
	ofstream fout("palsquare.out");

	int base;
	char sOrigin[32];
	char sPower[32];
	int high = 300;
	//fscanf(fin, "%d", &base);
	fin >> base;

	for (int i = 1; i <= high; ++i)
	{
		numConv(sPower, i*i, base);
		if (isPal(sPower))
		{
			numConv(sOrigin, i, base);
			//fprintf(fout, "%s %s\n", sOrigin, sPower);
			fout << string(sOrigin) << " " << string(sPower) << endl;
		}

	}

	return 0;
}