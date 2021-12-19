import time

start_time = time.time()

file = open('input.txt', 'r')
lines = file.read().splitlines()
file.close()


def q1(f):
    inpt = f.pop(0).split(',')
    boards = []
    index, col = -1, 0
    for line in f:
        if line == '':
            col = 0
            index += 1
            boards.append([])
        else:
            boards[index].append({})
            row = line.split()
            for i in range(5):
                if len(boards[index][col]) == 0:
                    boards[index][col] = {int(row[i]): False}
                else:
                    boards[index][col].update({int(row[i]): False})
            col += 1
    sc = 0
    winner = []
    breaker = False
    for draw in inpt:
        for board in boards:
            if breaker:
                # sc = int(draw)
                break
            for row in board:
                if int(draw) in row:
                    row[int(draw)] = True
                    break
                if all(value is True for value in row):
                    winner = board
                    breaker = True
                    sc = int(draw)
                    break
            for i in range(len(board)):
                if all(row[list(row)[i]] is True for row in board):
                    winner = board
                    breaker = True
                    sc = int(draw)
                    break
    win = 0
    for row in winner:
        for num in row:
            if not row[num]:
                win += num
    return win * sc


def q2(f):
    inpt = f.pop(0).split(',')
    boards = []
    index, col = -1, 0
    for line in f:
        if line == '':
            col = 0
            index += 1
            boards.append([])
        else:
            boards[index].append({})
            row = line.split()
            for i in range(5):
                if len(boards[index][col]) == 0:
                    boards[index][col] = {int(row[i]): False}
                else:
                    boards[index][col].update({int(row[i]): False})
            col += 1

    winner = []
    dw = 0
    for draw in inpt:
        if len(boards) == 1:
            break
        for board in boards:
            if board not in winner:
                if len(boards) == 1:
                    dw = draw
                    break
                for row in board:
                    if int(draw) in row:
                        row[int(draw)] = True
                        break
                    # check that all values in a row are True
                    if all(value is True for value in row):
                        winner.append(board)
                        boards.remove(board)
                        dw = draw
                        break
                for i in range(len(board)):
                    if all(row[list(row)[i]] is True for row in board):
                        winner.append(board)
                        boards.remove(board)
                        dw = draw
                        break
    print(boards[0])
    print(winner[-1])
    print(dw)
    win = 0
    for row in winner[-1]:
        for num in row:
            if not row[num]:
                win += num
    return win * int(dw)


print(q1(list(lines)))

time1 = time.time()
print("--- runtime %s seconds ---" % (time.time() - start_time))

print(q2(list(lines)))

print("--- runtime %s seconds ---" % (time.time() - time1))
