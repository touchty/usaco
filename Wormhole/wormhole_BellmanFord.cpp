/*POJ POJ 3259 Wormholes*/

#include <cstdio>  
#include <iostream>  
#include <algorithm>  
using namespace std;  
#define INF 1234567  
#define MAX_V 505  
#define MAX_E 5205  
  
struct edge{ int s, e, w; };  
edge es[MAX_E];  
int d[MAX_V];  
int E, V;  
  
bool Bellman_ford(int s){  
    for(int i=1; i<=V; i++) d[i] = (i==s) ? 0 : INF;  
    for(int i=0; i<V; i++){  
        for(int j=0; j<E; j++){  
            edge now = es[j];  
            if(d[now.e] > d[now.s] + now.w){  
                d[now.e] = d[now.s] + now.w;  
                if(i == V-1) return true;  
            }  
        }  
    }  
    return false;  
}  
  
  
  
int main(){  
//    freopen("in.txt", "r", stdin);  
    int t, n, m, w, s, e, time;  
    scanf("%d", &t);  
    while(t--){  
        scanf("%d%d%d", &n, &m, &w);  
        V = n;  
        E = 0;  
        for(int i=0; i<m; i++){            //路是双向的  
            scanf("%d%d%d", &s, &e, &time);  
            es[E].s = s;  
            es[E].e = e;  
            es[E++].w = time;  
            es[E].s = e;  
            es[E].e = s;  
            es[E++].w = time;  
        }  
        for(int i=0; i<w; i++){          //虫洞是单向！！！  
            scanf("%d%d%d", &s, &e, &time);  
            es[E].s = s;  
            es[E].e = e;  
            es[E++].w = -time;  
        }  
        printf("%s\n", Bellman_ford(1) ? "YES" : "NO");  
    }  
    return 0;  
} 