#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <string.h>
#include <stdio.h>

int main(int argc, char** argv) {
  int sockfd = socket(AF_INET, SOCK_DGRAM, 0);
  if (sockfd < 0) {
    printf("There was a problem creating the socket\n");
    return 1;
  }

  struct sockaddr_in serveraddr;
  serveraddr.sin_family=AF_INET;
  serveraddr.sin_port = htons(9876); // same number as tcp code but treated differently
  serveraddr.sin_addr.s_addr=inet_addr("127.0.0.1");

  printf("Enter a message: ");
  char line[5000];
  fgets(line, 5000, stdin);
  sendto(sockfd, line, strlen(line) + 1, 0, (struct sockaddr*)&serveraddr, sizeof(serveraddr)); // has additional two params for the address we are sending to
  
}
