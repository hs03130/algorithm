#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int N;
	cin >> N;
	int t[1005];
	for (int i = 0; i < N; i++) cin >> t[i];

	sort(t, t + N);

	int wating = 0, ans = 0;
	for (int i = 0; i < N; i++) {
		wating += t[i];
		ans += wating;
	}

	cout << ans;
}