#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int N, a[55], b[55];
	cin >> N;
	for (int i = 0; i < N; i++) cin >> a[i];
	for (int i = 0; i < N; i++) cin >> b[i];
	sort(a, a + N);
	sort(b, b + N, greater<>());
	
	int sum = 0;
	for (int i = 0; i < N; i++) sum += a[i] * b[i];
	cout << sum;
}