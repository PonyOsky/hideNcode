#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

/**
 * @brief one number in the hash
 *
 */
typedef struct {
  int num;
  bool known;
} num;

/**
 * @brief returns number of players choice in any menu
 *
 * @return int players choice
 */
int choice() {
  int choice;
  printf("\nType choice:");
  scanf("%d", &choice);
  printf("\n");
  return choice;
}

/**
 * @brief Get the Hash combination
 *
 * @param code pointer to hash
 * @param size size of hash
 */
void getHash(num *hash, int size) {
  for (int i = 0; i < size; i++) {
    hash[i].num = rand() % 10;
    hash[i].known = false;
  }
}

/**
 * @brief method checks attempt
 *
 * @param code user attempt
 * @param hash hash code
 * @param index actual position of number
 * @param size size of hash and code
 * @return int 0 if numbers on same position are same, 1 if number is into hash
 * but on other position, 2 if number is not into hash
 */
int contains(int *code, num *hash, int index, int size) {
  if (code[index] == hash[index].num) {
    hash[index].known = true;
    return 0;
  }
  for (int i = 0; i < size; i++) {
    if (code[index] == hash[i].num) {
      return 1;
    }
  }
  return 2;
}

/**
 * @brief checking if player wins
 *
 * @param hash hash combination
 * @param size size of hash
 * @return true when player wins
 * @return false when user hasn't won yet
 */
bool check(num *hash, int size) {
  for (int i = 0; i < size; i++) {
    if (!hash[i].known) {
      return false;
    }
  }
  return true;
}

/**
 * @brief one game round (exactly full game logic)
 *
 * @param hash pointer to hash
 * @param size size of code and hash
 * @return true when user want to play again
 * @return false when user want to go into menu
 */
bool playGame(num *hash, int size) {
  int attempts = 1;
  bool win = false;
  do {
    printf("ATTEMPT: %d\nHash:", attempts);
    for (int i = 0; i < size; i++) {
      if (hash[i].known) {
        printf(" %d", hash[i].num);
      } else {
        printf(" #");
      }
    }
    printf("\n");
    printf("Write code: ");
    int code[size];
    int *toCode = code;
    for (int i = 0; i < size; i++) {
      int number;
      scanf(" %d", &number);
      code[i] = number;
    }
    printf("\n");
    printf("Your try: ");
    for (int i = 0; i < size; i++) {
      switch (contains(toCode, hash, i, size)) {
      case 0:
        printf(" C");
        break;
      case 1:
        printf(" O");
        break;
      case 2:
        printf(" F");
        break;
      }
    }
    printf("\n\n");
    attempts++;
    win = check(hash, size);
  } while (!win);
  printf("YOU WIN!!!\nNumber of attempts: %d\n\n", attempts);
  printf("1\tPlay again\n0\tMenu\n");
  if (choice() == 1) {
    return true;
  } else {
    return false;
  }
}

/**
 * @brief pre-game function
 *
 * @return false when user want to go into menu
 */
bool game() {
restart:
  printf("How big code would you like to guess?\n");
  int size = choice();
  num hash[size];
  num *toHash = hash;
  getHash(toHash, size);
  if (playGame(toHash, size)) {
    goto restart;
  } else {
    return false;
  }
}

/**
 * @brief shows rules
 *
 * @return true for exit program
 * @return false when user want to go into menu
 */
bool rules() {
  printf("HOW TO PLAY\nComputer choose some combination of numnbers. Computer "
         "let you guess the right combination.\n");
  printf("Computer shows you number of #, it means one # one number between "
         "0-9.\n\nEvery one code should be unique. Every number can be "
         "specific or every number should be same.\n\n");
  printf("Computer is counting your attempts. Each every attempt you have to "
         "write combination.\nFor example:\nComputer writes ###, which means "
         "you must try some combination of three numbers.\n");
  printf("So something like this: 1 or 98 or 1236 is invalid input and "
         "computer say it to you. Only combinations like 123 or 506 are "
         "correct input.\n\n");
  printf("C\tcorrect hint, this number is on this position\nO\tcode contains "
         "this number but in defferent position\nF\tthis number in not in code "
         "combination\nType 0 for return to menu.");
  if (choice() != 0) {
    return true;
  } else {
    return false;
  }
}

/**
 * @brief main method
 *
 * @return program ending
 */
int main() {
  bool end = false;
menu:
  printf("HIDE N CODE\n\nType number:\n");
  printf("1\tStart game\n2\tHow to play\n3\tExit\n");
  int ch = choice();
  switch (ch) {
  case 1:
    end = game();
    break;
  case 2:
    end = rules();
    break;
  case 3:
    end = true;
    return 0;
  }
  if (!end) {
    goto menu;
  }
  return 0;
}