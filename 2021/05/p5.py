from time import time

start_time = time()

file = open('input.txt', 'r')
lines = file.read().splitlines()
# loop to split coordinates into pairs
for i in range(len(lines)):
    temp = lines[i].split(' -> ')
    tx, ty = temp[0].split(','), temp[1].split(',')
    lines[i] = [[tx[0], ty[0]], [tx[1], ty[1]]]
file.close()


# [[[x1, y1], [x2, y2]], [[x1, y1], [x2, y2]]...]
def q1(f):
    points = set()
    grid = []
    for coord in f:
        startx, starty, endx, endy = int(coord[0][0]), int(coord[0][1]), int(coord[1][0]), int(coord[1][1])
        # if (startx == endx) or (starty == endy):
        if starty == endy:
            for x in range(min(startx, endx), max(startx, endx)):
                try:
                    if grid[starty][x] == '.':
                        grid[starty][x] = 1
                    else:
                        grid[starty][x] += 1
                        t = [x, starty]
                        points.add(tuple(t))
                except IndexError:
                    for y1 in range(len(grid), max(starty, endy) + 1):
                        grid.append([0 for _ in range(max(startx, endx) + 1)])
                    grid[starty][x] += 1
        elif startx == endx:
            for y in range(min(starty, endy), max(starty, endy)):
                try:
                    if grid[y][startx] == '.':
                        grid[y][startx] = 1
                    else:
                        grid[y][startx] += 1
                        t = [startx, y]
                        points.add(tuple(t))
                except IndexError:
                    for y1 in range(len(grid), max(starty, endy) + 1):
                        grid.append([0 for _ in range(max(startx, endx) + 1)])
                    grid[y][startx] += 1
    for row in grid:
        print(*row)
    return len(points)


def q2(f):
    return


print(q1(list(lines)))

time1 = time()
print("--- runtime %s seconds ---" % (time() - start_time))

# print(q2(list(lines)))

# print("--- runtime %s seconds ---" % (time.time() - time1))
