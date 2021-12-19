def q1():
    file = open('input.txt', 'r')
    horizontal = 0
    depth = 0

    for line in file:
        for word in line.split():
            if word.isdigit():
                x = int(word)
        if 'forward' in line:
            horizontal += x
        elif 'up' in line:
            depth -= x
        elif 'down' in line:
            depth += x
    
    print(horizontal * depth)
    file.close()

def q2():
    file = open('input.txt', 'r')
    horizontal = 0
    depth = 0
    aim = 0

    for line in file:
        for word in line.split():
            if word.isdigit():
                x = int(word)
        if 'forward' in line:
            horizontal += x
            depth += (aim * x)
        elif 'up' in line:
            aim -= x
        elif 'down' in line:
            aim += x
            
    print(horizontal * depth)
    file.close()

q1()
q2()
