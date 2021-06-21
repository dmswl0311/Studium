import React from "react"
import PropTypes from "prop-types"
import './Member.css'


function Member( {image, name, city, gender, display} ){
    if(display === "thum-event"){
        return (
            <div>
                <div className="thum-event-member-image">
                        <img src={image}></img>
                </div>
                <div className="thum-event-member-name">
                        <p className="member-name">{name}</p>
                        <p className="member-city">{city}</p>
                </div>
            </div>
        )
    } else if(display === "thum-group"){
        return (
            <div>
                <div className="thum-group-member-image">
                        <img src={image}></img>
                </div>
                <div className="thum-group-member-name">
                        <p className="member-name">{name}</p>
                </div>
            </div>
        )
    } else if(display === "detail-inGroup"){
        return (
            <div className="detail-inGroup">
                <h2>스터디 회원 정보</h2>
                <div className="detail-inGroup-image">
                    <img src={image} arl="" />
                </div>
                <div className="detail-inGroup-info">
                    <span>이름 : {name}</span>
                    <span>성별 : {gender}</span>
                    <span>지역 : {city}</span>
                </div>
            </div>
        )
    } else if(display === "mypage") {
        return (
            <div>
                 <div className="detail-inGroup">
                    <h2>스터디 회원 정보</h2>
                    <div className="detail-inGroup-image">
                        <img src={image} arl="" />
                    </div>
                    <div className="detail-inGroup-info">
                        <span>이름 : {name}</span>
                        <span>성별 : {gender}</span>
                        <span>지역 : {city}</span>
                    </div>
                </div>
            </div>
        )
    }
}

Member.propTypes = {
    image: PropTypes.string,
    name: PropTypes.string,
    gender: PropTypes.string,
    city: PropTypes.string,
    display: PropTypes.string
}

export default Member;