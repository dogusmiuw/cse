#include <iostream>
#include <fstream>
using namespace std;
bool like(string s1, string s2)
{
    if (s1[0] == '8' or s2[0] == '8')
        return true;
    if (s1[0] == s2[0] or s1[1] == s2[1])
        return true;
    return false;
}
void crazyUp(string deck[])
{
    int dp[52]; /*     Stores length of allowable sequnce     starting at current index     */
    for (int i = 0; i < 52; i++)
        dp[i] = 1;
    for (int i = 51; i >= 0; i--)
    {
        for (int j = i + 1; j < 52; j++)
        {
            if (like(deck[i], deck[j]))
                dp[i] = max(dp[i], 1 + dp[j]);
        }
    }
    int beg, end, mx = 0;
    for (int i = 0; i < 52; i++)
    {
        if (dp[i] > mx)
        {
            mx = dp[i];
            beg = i;
        }
    }
    end = beg;
    while (dp[end] != 1)
    {
        for (int j = end + 1; j < 52; j++)
        {
            if (like(deck[end], deck[j]) and dp[end] == dp[j] + 1)
            {
                end = j;
                break;
            }
        }
    }
    cout << "Max length of allowable sequence : " << mx << endl;
    cout << "Sequence begins at : " << deck[beg] << endl;
    cout << "Sequence ends at: " << deck[end] << endl;
}
int main()
{
    string fname;
    cout << "Enter file name:";
    cin >> fname;
    ifstream inp(fname);
    string deck[52];
    for (int i = 0; i < 52; i++)
        inp >> deck[i];
    crazyUp(deck);
}

/*
Only 43 lines are visible in the above input. Running with deck of 43 instead of 52 gives the following output.

 Enter file name: Ex9.txt Max length of allowable sequence: 20 Sequence begins at: AS Sequence ends at: 7C
 */