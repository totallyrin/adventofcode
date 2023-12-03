from ...input import read_lines


def count_cubes(game):
    print()


lines = read_lines("2023/02/part_1/sample.txt")
# lines = read_lines("2023/02/part_1/input.txt")

for i in range(len(lines)):
    # remove "Game x: " from string, since game # is index + 1
    lines[i] = lines[i].replace('Game ' + str(i + 1) + ": ", "")
    # split sets
    lines[i] = lines[i].split("; ")

print(lines)
