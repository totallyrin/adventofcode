import pprint

from ..input import read_lines


def format_games(lines):
    # format games
    for i in range(len(lines)):
        # remove "Game x: " from string, since game # is index + 1
        lines[i] = lines[i].replace('Game ' + str(i + 1) + ": ", "")
        # split sets
        lines[i] = lines[i].split("; ")
        # split sets into dict?
        for j in range(len(lines[i])):
            dict = {}
            s = lines[i][j].split(", ")
            for single in s:
                temp = single.split(" ")
                if temp[1] in dict.keys():
                    dict[temp[1]] += int(temp[0])
                else:
                    dict[temp[1]] = int(temp[0])
            lines[i][j] = dict
    return lines


def part_1(lines):
    lines = format_games(lines)
    impossible = []
    cubes = {"red": 12, "green": 13, "blue": 14}
    # check which games are possible
    for game in lines:
        # for each round in a game:
        for round in game:
            for colour in round:
                if round[colour] > cubes[colour]:
                    # get game #s of impossible games
                    if (lines.index(game) + 1) not in impossible:
                        impossible.append(lines.index(game) + 1)
                    break

    # add up all possible games
    sum = 0
    for i in range(len(lines)):
        if (i + 1) not in impossible:
            sum += i + 1
    return sum


def part_2(lines):
    lines = format_games(lines)
    sum = 0
    for game in lines:
        colours_min = {"red": 0, "green": 0, "blue": 0}
        for round in game:
            for colour in round:
                if round[colour] > colours_min[colour]:
                    colours_min[colour] = round[colour]
        power = 1
        for colour in colours_min:
            power *= colours_min[colour]
        sum += power
    return sum


pp = pprint.PrettyPrinter(indent=4)

# l = read_lines("2023/02/sample.txt")
l = read_lines("2023/02/input.txt")

print(part_1(l.copy()))
print(part_2(l.copy()))
