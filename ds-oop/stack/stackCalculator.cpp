#include <iostream>
#include <stack>
#include <sstream>
#include <string>

using namespace std;

// Fungsi untuk mengevaluasi ekspresi postfix
int evaluatePostfix(const string &expression)
{
    stack<int> s;
    stringstream ss(expression);
    string token;

    while (ss >> token)
    {
        if (isdigit(token[0]))
        { // Jika token adalah angka
            s.push(stoi(token));
        }
        else
        { // Jika token adalah operator
            if (s.size() < 2)
            {
                throw invalid_argument("Ekspresi tidak valid");
            }

            int b = s.top();
            s.pop();
            int a = s.top();
            s.pop();

            if (token == "+")
            {
                s.push(a + b);
            }
            else if (token == "-")
            {
                s.push(a - b);
            }
            else if (token == "x" || token == "*")
            {
                s.push(a * b);
            }
            else if (token == "/")
            {
                if (b == 0)
                {
                    throw invalid_argument("Pembagian oleh nol");
                }
                s.push(a / b);
            }
            else
            {
                throw invalid_argument("Operator tidak dikenal: " + token);
            }
        }
    }

    if (s.size() != 1)
    {
        throw invalid_argument("Ekspresi tidak valid");
    }

    return s.top();
}

int main()
{
    string expression = "5 6 + 1 2 + 4 2 x - x";

    try
    {
        int result = evaluatePostfix(expression);
        cout << "Hasil ekspresi \"" << expression << "\" adalah: " << result << endl;
    }
    catch (const exception &e)
    {
        cerr << "Error: " << e.what() << endl;
    }

    return 0;
}
