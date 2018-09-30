  program Convert
  implicit none
  ! -----------------------------------------------Declare
  real*4 pi, x, y, distance, start, finish
  integer*4 number, i, inside

  ! -----------------------------------------------Input
  print*, "Enter number of points ..."
  read*, number
  ! -----------------------------------------------Compute
  call cpu_time(start)
  pi = 0
  inside = 0
  do i=1,number
    x = rand(0)
    y = rand(0)
    distance = sqrt(x**2 + y**2)
    if (distance < 1) then
      inside = inside + 1
    endif
    
  enddo
  pi = (real(inside) / real(number))*4
  call cpu_time(finish)
  ! -----------------------------------------------Output
  print*, "The computed value for Pi is "
  print*, pi, " ."
  print '("Time = ",f6.3," seconds.")',finish-start
  end 