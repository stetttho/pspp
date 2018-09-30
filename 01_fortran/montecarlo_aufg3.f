  program Convert
  use omp_lib
  implicit none
  ! -----------------------------------------------Declare
  real*4 pi, x, y, distance, start, finish, ran0
  integer*4 number, i, inside, inside_local, tid, ANZ_THREADS
  parameter (ANZ_THREADS = 4)

  ! -----------------------------------------------Input
  print*, "Enter number of points ..."
  read*, number
  ! -----------------------------------------------Compute
  call cpu_time(start)
  pi = 0
  inside = 0
  !$omp parallel private(x,y,distance, inside_local) num_threads(ANZ_THREADS)
  tid = omp_get_thread_num()
  inside_local = 0
  do i=1,number/ANZ_THREADS
    x = ran0(tid)
    y = ran0(tid)
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

  function ran0(seed)
! Park & Miller Random Generator
integer seed,ia,im,iq,ir,mask,k
real ran0,am
parameter (ia=16807,im=2147483647,am=1./im,iq=127773,ir=2836,mask=123459876)
seed=ieor(seed,mask)
k=seed/iq
seed=ia*(seed-k*iq)-ir*k
if (seed.lt.0) seed=seed+im
ran0=am*seed
seed=ieor(seed,mask)
return
end



