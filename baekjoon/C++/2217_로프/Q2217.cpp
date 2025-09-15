#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int N, rope[100005];
	cin >> N;
	for (int i = 0; i < N; i++) cin >> rope[i];

	sort(rope, rope + N);
	int k = 0;
	for (int i = 0; i < N; i++) {
		int value = rope[i] * (N - i);
		if (rope[k] * (N - k) < value) k = i;
	}
	cout << rope[k] * (N - k);
}