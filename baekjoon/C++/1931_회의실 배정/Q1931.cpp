#include <bits/stdc++.h>
using namespace std;
#define end first
#define start second

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int N;
	cin >> N;
	pair<int, int> time[100005];
	for (int i = 0; i < N; i++) {
		int s, e;
		cin >> s >> e;
		time[i] = { e, s };
	}
	sort(time, time + N);
	int cnt = 0, endTime = -1;
	for (int i = 0; i < N; i++) {
		if (time[i].start < endTime) continue;
		endTime = time[i].end;
		cnt++;
	}
	cout << cnt;
}