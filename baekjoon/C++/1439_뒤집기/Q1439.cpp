#include <bits/stdc++.h>
using namespace std;

string line;
int len;
int cnt[2];

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> line;
	len = line.length();
	cnt[line[0] - '0']++;
	for (int idx = 1; idx < len; idx++) {
		if (line[idx - 1] != line[idx])
			cnt[line[idx] - '0']++;
	}

	cout << min(cnt[0], cnt[1]);
}