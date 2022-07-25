import React from 'react'
import './NavBar.css'

function NavBar() {
  return (
    <>
        <div className='navbar-wrapper'>
            <div className='navbar-left'>
                <img className='navbar-logo-img' alt='#' src={`${process.env.PUBLIC_URL}/img/sellerB_Logo.svg`} />
                <div className='small-sellerb'>SellerB</div>
            </div>
            <div className='navbar-right'>
                <div className='navbar-right-navigator'>
                    <h4>공지사항</h4>
                    <h4>회의 참여</h4>
                    <h4>마이페이지</h4>
                    <h4>로그아웃</h4>

                </div>
            </div>
        </div>
    </>
  )
}

export default NavBar