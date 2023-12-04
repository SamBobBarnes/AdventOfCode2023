export DAY=$1
export PART=1

# Create folder
mkdir "./AOC/src/Day${DAY}"

# Write Part1
filename="./AOC/src/Day${DAY}/Part${PART}.java"
envsubst <./Make/TemplatePart.java > "${filename}"

# Write Part2
export PART=2
filename="./AOC/src/Day${DAY}/Part${PART}.java"
envsubst <./Make/TemplatePart.java > "${filename}"



head -n -1 ./AOC/tests/MainRunnerTests.java > temp.java
mv temp.java ./AOC/tests/MainRunnerTests.java

envsubst <./Make/TestTemplate.java >> ./AOC/tests/MainRunnerTests.java

mkdir "./AOC/input/Day${DAY}"

touch "./AOC/input/Day${DAY}/Input.txt"
touch "./AOC/input/Day${DAY}/ExampleInput.txt"

git add .
git commit -m "day ${DAY} init"

exit 0