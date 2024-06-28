import React from "react";
import "./FavoriteCharacter.css"

export interface FavoriteCharacterProps {
    favoriteCharacter: string,
    setFavoriteCharacter: (favoriteCharacter: string) => void
}

const FavoriteCharacter = (props: FavoriteCharacterProps) => {
    const { favoriteCharacter, setFavoriteCharacter, ...rest } = props;

    return (
        <div className="favorite-character-container">
            <label className="favorite-character-label" htmlFor="favorite-characters">
                좋아하는 캐릭터(일기로 제작한 만화에 나왔으면 좋겠다고 생각하는 캐릭터)
            </label>
            <input
                className="favorite-character-input"
                value={favoriteCharacter}
                onChange={(e) => setFavoriteCharacter(e.target.value)}
            />
        </div>
    )
}

export default FavoriteCharacter;
