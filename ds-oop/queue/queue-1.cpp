#include <iostream>
#include <queue>

class QueueManager
{
private:
  std::queue<int> q;

public:
  void enqueue(int value)
  {
    q.push(value);
  }

  void dequeue()
  {
    if (!q.empty())
    {
      std::cout << q.front() << " ";
      q.pop();
    }
  }

  void processQueue()
  {
    enqueue(5);
    enqueue(10);
    enqueue(15);
    dequeue();
    enqueue(20);
    dequeue();
    q.push(q.front() + 5);
    dequeue();
    dequeue();
    enqueue(50);
    q.push(q.back() - 10);
    dequeue();
    dequeue();
    dequeue();

    std::cout << std::endl;
  }
};

int main()
{
  QueueManager qm;
  qm.processQueue();
  return 0;
}
