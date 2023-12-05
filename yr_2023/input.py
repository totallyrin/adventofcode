from typing import List


def read_lines(f: str) -> List[str]:
    file = open(f, "r")
    lines = []
    for line in file:
        if line == "\n":
            continue
        lines.append(line.strip())
    return lines
