file = open('input.txt', 'r')
lines = file.read().splitlines()
file.close()


def q1(file):
    gamma, epsilon = '', ''
    for i in range(len(file[0])):
        temp = []
        for line in file:
            temp.append(line[i])
        count1, count0 = 0, 0
        for num in temp:
            if int(num) == 1:
                count1 += 1
            elif int(num) == 0:
                count0 += 1
        if count1 > count0:
            gamma += '1'
            epsilon += '0'
        else:
            gamma += '0'
            epsilon += '1'
    print(int(gamma, 2) * int(epsilon, 2))


def q1alt(file):
    l = len(file[0])
    h = len(file)
    g = [0] * l
    for i in range(h):
        for j in range(l):
            if file[i][j] == '1':
                g[j] += 1
    gamma = 0
    epsilon = 0
    for i in range(l):
        gamma *= 2
        epsilon *= 2
        gamma += 1 if g[i] > h - g[i] else 0
        epsilon += 0 if g[i] > h - g[i] else 1
    print(gamma * epsilon)


def ox(file):
    oxygen = file
    for i in range(len(file[0])):
        if len(oxygen) == 1:
            break
        zero = []
        one = []
        for item in oxygen:
            if int(item[i]) == 0:
                zero.append(item)
            elif int(item[i]) == 1:
                one.append(item)
        if len(one) >= len(zero):
            oxygen = list(one)
        else:
            oxygen = list(zero)
    return int(oxygen[0], 2)


def co(file):
    co2 = file
    for i in range(len(file[0])):
        if len(co2) == 1:
            break
        zero = []
        one = []
        for item in co2:
            if int(item[i]) == 0:
                zero.append(item)
            elif int(item[i]) == 1:
                one.append(item)
        if len(zero) <= len(one):
            co2 = list(zero)
        else:
            co2 = list(one)
    return int(co2[0], 2)


def q2(file):
    print(ox(file) * co(file))


q1(lines)
q2(lines)
