export const authcheck = (to, from, next) => {
  if(window.sessionStorage.getItem('isAdmin')){
    next()}
    else{
      if (window.sessionStorage.getItem('isLogin')) {
        next()
      }else{
        next({path:'/login'})
      }
      }
    }
