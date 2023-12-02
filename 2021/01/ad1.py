def q1(arr):
    count = 0
    temp = 0
    for line in arr:
        if not temp == 0:
            if int(line) > temp:
                count += 1
        temp = int(line)
    return count


def q2(arr):
    sums = []
    for i in range(len(arr)):
        sums.append(0)
        if not((i + 3) > len(arr)):
            for j in range(3):
                sums[i] += int(arr[i + j])
        else:
            for j in range((len(arr) - i)):
                sums[i] += int(arr[i + j])
    count = 0
    temp = 0
    for num in sums:
        if not temp == 0:
            if num > temp:
                count += 1
        temp = num
    return count


file = open('input.txt', 'r')
arr = file.readlines()
print(q1(arr))
print(q2(arr))
file.close()
