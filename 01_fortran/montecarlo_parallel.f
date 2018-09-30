  program Convert
  use omp_lib
  implicit none
  ! -----------------------------------------------Declare
  real*4 pi, x, y, distance, start, finish
  integer*4 number, i, inside, inside_local, ANZ_THREADS
  parameter (ANZ_THREADS = 4)

  ! -----------------------------------------------Input
  print*, "Enter number of points ..."
  read*, number
  ! -----------------------------------------------Compute
  call cpu_time(start)
  pi = 0
  inside = 0
  !$omp parallel private(x,y,distance, inside_local) num_threads(ANZ_THREADS)
  inside_local = 0
  do i=1,number/ANZ_THREADS
    x = rand(0)
    y = rand(0)
    distance = sqrt(x**2 + y**2)
    
    if (distance < 1) then
      inside_local = inside_local + 1
    endif
    
    
  enddo
  !$omp critical
  print*, inside_local
  inside = inside + inside_local
  !$omp end critical

  !$omp end parallel
  pi = (real(inside) / real(number))*4
  call cpu_time(finish)
  ! -----------------------------------------------Output
  print*, "The computed value for Pi is "
  print*, pi, " ."
  print '("Time = ",f6.3," seconds.")',finish-start
  end 



